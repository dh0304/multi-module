package com.cafegory.auth.service;

import com.cafegory.auth.domain.JwtToken;
import com.cafegory.auth.implement.LoginProcessor;
import com.cafegory.auth.implement.SignupProcessor;
import com.cafegory.auth.infrastructure.aws.AwsS3Client;
import com.cafegory.auth.infrastructure.oauth2.OAuth2Client;
import com.cafegory.auth.infrastructure.oauth2.dto.OAuth2Profile;
import com.cafegory.auth.infrastructure.oauth2.dto.OAuth2TokenRequest;
import com.cafegory.auth.util.ImageData;
import com.cafegory.auth.util.ImageDownloadUtil;
import com.cafegory.domain.common.exception.CafegoryException;
import com.cafegory.domain.member.domain.Member;
import com.cafegory.domain.member.domain.MemberContent;
import com.cafegory.domain.member.domain.MemberId;
import com.cafegory.domain.member.implement.MemberEditor;
import com.cafegory.domain.member.implement.MemberReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

	private final OAuth2Client oAuth2Client;
	private final AwsS3Client awsS3Client;

	private final MemberReader memberReader;
	private final MemberEditor memberEditor;

	private final LoginProcessor loginProcessor;
	private final SignupProcessor signupProcessor;

	@Transactional
	public JwtToken socialLogin(OAuth2TokenRequest oAuth2TokenRequest) {
		OAuth2Profile profile = oAuth2Client.fetchMemberProfile(oAuth2TokenRequest);
		JwtToken token = loginOrSignup(profile);
		updateMemberRefreshTokenAndProfile(profile, token);

		return token;
	}

	private JwtToken loginOrSignup(OAuth2Profile profile) {
		try {
			return loginProcessor.login(profile.getEmailAddress());
		} catch (CafegoryException e) {
			signupProcessor.signup(profile.getEmailAddress(), profile.getNickName());
			return loginProcessor.login(profile.getEmailAddress());
		}
	}

	private void updateMemberRefreshTokenAndProfile(OAuth2Profile profile, JwtToken token) {
		Member member = memberReader.read(profile.getEmailAddress());

		String profileUrl = uploadProfileImageToS3(profile.getProfileImgUrl());

		memberEditor.updateRefreshToken(new MemberId(member.getId().getId()), token.getRefreshToken());
		memberEditor.edit(createMemberContent(profileUrl), new MemberId(member.getId().getId()));
	}

	private MemberContent createMemberContent(String imgUrl) {
		return MemberContent.builder()
			.imgUrl(imgUrl)
			.build();
	}

	private String uploadProfileImageToS3(String imageUrl) {
		ImageData imageData = ImageDownloadUtil.downloadImage(imageUrl);
		String filename = UUID.randomUUID().toString();

		awsS3Client.uploadImageToS3(filename, imageData);
		return awsS3Client.getUrl(filename);
	}
}
