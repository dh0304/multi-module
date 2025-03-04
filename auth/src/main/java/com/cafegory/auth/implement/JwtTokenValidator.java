package com.cafegory.auth.implement;

import com.cafegory.auth.domain.JwtClaims;
import com.cafegory.auth.exception.JwtTokenAuthenticationException;
import com.cafegory.domain.common.exception.ExceptionType;
import org.springframework.stereotype.Component;

import static com.cafegory.auth.domain.TokenClaims.SUBJECT;
import static com.cafegory.domain.common.exception.ExceptionType.JWT_ACCESS_SUB_AND_REFRESH_SUB_NOT_MATCHED;


@Component
public class JwtTokenValidator {

	public void validateNullToken(final String token, ExceptionType exceptionType) {
		if (token == null) {
			throw new JwtTokenAuthenticationException(exceptionType);
		}
	}

	public void validateTokenSubjectMatch(final JwtClaims accessTokenClaims, final JwtClaims refreshTokenClaims) {
		String accessTokenSubject = accessTokenClaims.getClaim(SUBJECT.getValue());
		String refreshTokenSubject = refreshTokenClaims.getClaim(SUBJECT.getValue());

		if (!accessTokenSubject.equals(refreshTokenSubject)) {
			throw new JwtTokenAuthenticationException(JWT_ACCESS_SUB_AND_REFRESH_SUB_NOT_MATCHED);
		}
	}
}
