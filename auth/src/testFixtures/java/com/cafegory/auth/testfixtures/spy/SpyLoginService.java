package com.cafegory.auth.testfixtures.spy;

import com.cafegory.auth.domain.JwtToken;
import com.cafegory.auth.implement.LoginProcessor;
import com.cafegory.auth.implement.SignupProcessor;
import com.cafegory.auth.infrastructure.oauth2.dto.OAuth2Profile;
import com.cafegory.auth.infrastructure.oauth2.dto.OAuth2TokenRequest;
import com.cafegory.auth.service.LoginService;
import com.cafegory.domain.common.exception.CafegoryException;
import com.cafegory.domain.member.domain.Member;
import com.cafegory.domain.member.domain.MemberContent;
import com.cafegory.domain.member.domain.MemberId;
import com.cafegory.domain.member.implement.MemberEditor;
import com.cafegory.domain.member.implement.MemberReader;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
public class SpyLoginService implements LoginService {

	private final MemberReader memberReader;

	private final LoginProcessor loginProcessor;
	private final SignupProcessor signupProcessor;
	private final MemberEditor memberEditor;

	@Transactional
	public JwtToken socialLogin(OAuth2TokenRequest oAuth2TokenRequest) {
		OAuth2Profile profile = new OAuth2Profile() {
			@Override
			public String getNickName() {
				return "testNickname";
			}

			@Override
			public String getProfileImgUrl() {
				return "testProfileImgUrl";
			}

			@Override
			public String getEmailAddress() {
				return "test@gmail.com";
			}
		};

		JwtToken token = loginOrSignup(profile);

		Member member = memberReader.read(profile.getEmailAddress());
		memberEditor.updateRefreshToken(new MemberId(member.getId().getId()), token.getRefreshToken());

		String filename = UUID.randomUUID().toString();
		memberEditor.edit(
			MemberContent.builder()
				.imgUrl(filename)
				.build()
			, new MemberId(member.getId().getId())
		);
		//		member.changeProfileUrl(filename);

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
}
