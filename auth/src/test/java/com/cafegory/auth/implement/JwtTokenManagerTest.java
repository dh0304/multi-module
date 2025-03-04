package com.cafegory.auth.implement;

import com.cafegory.auth.domain.JwtClaims;
import com.cafegory.auth.exception.JwtTokenAuthenticationException;
import com.cafegory.domain.common.exception.ExceptionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

import static com.cafegory.auth.testfixtures.builder.JwtTokenBuilder.aToken;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class JwtTokenManagerTest {

	private final String testSecret = "01234567890123456789012345678901234567890123456789";
	private final JwtTokenManager sut = new JwtTokenManager(testSecret);

	@Test
	@DisplayName("JWT 토큰을 검증한다.")
	void verify_jwt() {
		//given
		String jwt = aToken()
			.withClaims(Map.of("a", "a", "b", "b"))
			.withIssuedAt(Date.from(Instant.now()))
			.withExpiration(3600)
			.withKey(testSecret)
			.build();
		//when
		JwtClaims claims = sut.verifyAndExtractClaims(jwt);
		//then
		assertAll(
			() -> assertThat(claims.getClaim("a")).isEqualTo("a"),
			() -> assertThat(claims.getClaim("b")).isEqualTo("b")
		);
	}

	@Test
	@DisplayName("JWT 토큰이 만료되었는지 검증한다.")
	void verify_expired_jwt() {
		//given
		String jwt = aToken()
			.withExpiration(0)
			.withKey(testSecret)
			.build();
		//when & then
		assertThatThrownBy(() -> sut.verifyAndExtractClaims(jwt))
			.isInstanceOf(JwtTokenAuthenticationException.class)
			.hasMessage(ExceptionType.JWT_EXPIRED.getErrorMessage());
	}

	@Test
	@DisplayName("JWT의 Claim이 유효하다.")
	void claim_is_valid() {
		//given
		String jwt = aToken()
			.withClaims(Map.of("tokenType", "access"))
			.withIssuedAt(Date.from(Instant.now()))
			.withExpiration(3600)
			.withKey(testSecret)
			.build();
		//when & then
		assertDoesNotThrow(() -> sut.validateClaim(jwt, "tokenType", "access"));
	}

	@Test
	@DisplayName("JWT의 Claim이 유효하지 않다.")
	void claim_is_invalid() {
		//given
		String jwt = aToken()
			.withClaims(Map.of("tokenType", "access"))
			.withIssuedAt(Date.from(Instant.now()))
			.withExpiration(3600)
			.withKey(testSecret)
			.build();
		//when & then
		assertThatThrownBy(() -> sut.validateClaim(jwt, "tokenType", "refreshToken"))
			.isInstanceOf(JwtTokenAuthenticationException.class)
			.hasMessage(ExceptionType.JWT_CLAIM_INVALID.getErrorMessage());
	}
}
