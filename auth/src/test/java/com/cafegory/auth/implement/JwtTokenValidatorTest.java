package com.cafegory.auth.implement;

import com.cafegory.auth.domain.JwtClaims;
import com.cafegory.auth.domain.TokenClaims;
import com.cafegory.auth.exception.JwtTokenAuthenticationException;
import com.cafegory.auth.testfixtures.builder.TestJwtFactory;
import com.cafegory.domain.common.exception.ExceptionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class JwtTokenValidatorTest {

	private final String testSecret = "01234567890123456789012345678901234567890123456789";
	private final JwtTokenManager jwtTokenManager = new JwtTokenManager(testSecret);

	@Test
	@DisplayName("두개의 토큰 클레임의 subject가 일치한다.")
	void two_subject_matches() {
		//given
		JwtTokenValidator sut = new JwtTokenValidator();

		String jwtToken1 = createJwtToken("a");
		String jwtToken2 = createJwtToken("a");

		JwtClaims jwtClaims1 = jwtTokenManager.verifyAndExtractClaims(jwtToken1);
		JwtClaims jwtClaims2 = jwtTokenManager.verifyAndExtractClaims(jwtToken2);
		//when
		assertDoesNotThrow(() -> sut.validateTokenSubjectMatch(jwtClaims1, jwtClaims2));
	}

	@Test
	@DisplayName("두개의 토큰 클레임의 subject가 일치하지 않는다.")
	void two_subject_does_not_match() {
		//given
		JwtTokenValidator sut = new JwtTokenValidator();

		String jwtToken1 = createJwtToken("a");
		String jwtToken2 = createJwtToken("b");

		JwtClaims jwtClaims1 = jwtTokenManager.verifyAndExtractClaims(jwtToken1);
		JwtClaims jwtClaims2 = jwtTokenManager.verifyAndExtractClaims(jwtToken2);
		//then
		assertThatThrownBy(() -> sut.validateTokenSubjectMatch(jwtClaims1, jwtClaims2))
			.isInstanceOf(JwtTokenAuthenticationException.class)
			.hasMessage(ExceptionType.JWT_ACCESS_SUB_AND_REFRESH_SUB_NOT_MATCHED.getErrorMessage());
	}

	private String createJwtToken(String subjectValue) {
		return TestJwtFactory.createAccessToken(
			Map.of(TokenClaims.SUBJECT.getValue(), subjectValue),
			Date.from(Instant.now()),
			3600,
			testSecret
		);
	}
}