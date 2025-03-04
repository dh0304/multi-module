package com.cafegory.auth.service;

import com.cafegory.auth.domain.JwtAccessToken;
import com.cafegory.auth.domain.JwtClaims;
import com.cafegory.auth.exception.JwtTokenAuthenticationException;
import com.cafegory.auth.implement.JwtCafegoryTokenManager;
import com.cafegory.auth.implement.JwtTokenManager;
import com.cafegory.auth.implement.JwtTokenValidator;
import com.cafegory.domain.common.exception.ExceptionType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.cafegory.auth.domain.TokenClaims.*;
import static com.cafegory.domain.common.exception.ExceptionType.JWT_ACCESS_TOKEN_MISSING;
import static com.cafegory.domain.common.exception.ExceptionType.JWT_EXPIRED;


@Service
@RequiredArgsConstructor
@Slf4j
public class JwtTokenManagementService {

	private final JwtCafegoryTokenManager jwtCafegoryTokenManager;
	private final JwtTokenManager jwtTokenManager;
	private final JwtTokenValidator jwtTokenValidator;

	public JwtAccessToken verifyAndRefreshAccessToken(final String accessToken, final String refreshToken) {
		jwtTokenValidator.validateNullToken(accessToken, JWT_ACCESS_TOKEN_MISSING);
		jwtTokenValidator.validateNullToken(refreshToken, ExceptionType.JWT_REFRESH_TOKEN_MISSING);

		JwtClaims accessTokenClaims = verifyAndExtractAccessClaims(accessToken);
		JwtClaims refreshTokenClaims = verifyAndExtractRefreshClaims(refreshToken);

		jwtTokenValidator.validateTokenSubjectMatch(accessTokenClaims, refreshTokenClaims);

		return jwtCafegoryTokenManager.createAccessToken(
			Map.of(SUBJECT.getValue(), refreshTokenClaims.getSubject())
		);
	}

	private JwtClaims verifyAndExtractRefreshClaims(final String refreshToken) {
		jwtTokenManager.validateClaim(refreshToken, TOKEN_TYPE.getValue(), REFRESH_TOKEN.getValue());
		return jwtTokenManager.verifyAndExtractClaims(refreshToken);
	}

	private JwtClaims verifyAndExtractAccessClaims(final String accessToken) {
		try {
			return jwtTokenManager.verifyAndExtractClaims(accessToken);
		} catch (JwtTokenAuthenticationException e) {
			if (e.getExceptionType() == JWT_EXPIRED) {
				return e.getClaims();
			}
			throw e;
		}
	}
}
