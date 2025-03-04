package com.cafegory.auth.implement;

import com.cafegory.domain.member.domain.Member;
import com.cafegory.domain.member.implement.MemberReader;
import com.cafegory.auth.domain.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.cafegory.auth.domain.TokenClaims.SUBJECT;

@Component
@RequiredArgsConstructor
public class LoginProcessor {

	private final JwtCafegoryTokenManager jwtCafegoryTokenManager;
	private final MemberReader memberReader;

	public JwtToken login(String email) {
		Member member = memberReader.read(email);

		return jwtCafegoryTokenManager.createAccessAndRefreshToken(
			Map.of(SUBJECT.getValue(), String.valueOf(member.getId().getId()))
		);
	}
}
