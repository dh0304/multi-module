package com.cafegory.auth.implement;

import com.cafegory.auth.domain.JwtClaims;
import com.cafegory.auth.exception.JwtTokenAuthenticationException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.cafegory.domain.common.exception.ExceptionType.*;


@RequiredArgsConstructor
@Slf4j
public final class JwtTokenManager {

	private final String secretKey;

	public JwtBuilder newTokenBuilder() {
		return new JwtBuilder(this.secretKey);
	}

	public static final class JwtBuilder {

		private final String secretKey;
		private final String type = "JWT";
		private final Map<String, Object> claims = new HashMap<>();
		private Date issuedAt = new Date();
		private int lifeTimeAsSeconds;

		public JwtBuilder(final String secretKey) {
			this.secretKey = secretKey;
		}

		public JwtBuilder addClaim(final String key, Object value) {
			this.claims.put(key, value);
			return this;
		}

		public JwtBuilder addAllClaims(final Map<String, Object> claims) {
			this.claims.putAll(claims);
			return this;
		}

		public JwtBuilder issuedAt(final Date issuedAt) {
			this.issuedAt = issuedAt;
			return this;
		}

		public JwtBuilder lifeTimeAsSeconds(final int lifeTimeAsSeconds) {
			this.lifeTimeAsSeconds = lifeTimeAsSeconds;
			return this;
		}

		public String build() {
			return Jwts.builder()
				.header().type(this.type).and()
				.claims(this.claims)
				.issuedAt(this.issuedAt)
				.expiration(Date.from(this.issuedAt.toInstant().plusSeconds(this.lifeTimeAsSeconds)))
				.signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
				.compact();
		}
	}

	public JwtClaims verifyAndExtractClaims(final String jwt) {
		try {
			Jws<Claims> jws = Jwts.parser()
				.verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
				.build()
				.parseSignedClaims(jwt);
			return convertClaimsToJwtClaims(jws.getPayload());
		} catch (ExpiredJwtException e) {
			throw new JwtTokenAuthenticationException(JWT_EXPIRED, e, convertClaimsToJwtClaims(e.getClaims()));
		} catch (JwtException e) {
			throw new JwtTokenAuthenticationException(JWT_INVALID, e);
		}
	}

	private JwtClaims convertClaimsToJwtClaims(Claims claims) {
		return new JwtClaims(claims);
	}

	public void validateClaim(final String jwt, final String key, final String value) {
		try {
			Jwts.parser()
				.verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
				.require(key, value)
				.build()
				.parse(jwt);
		} catch (ExpiredJwtException e) {
			throw new JwtTokenAuthenticationException(JWT_EXPIRED, e, convertClaimsToJwtClaims(e.getClaims()));
		} catch (InvalidClaimException e) {
			throw new JwtTokenAuthenticationException(JWT_CLAIM_INVALID, e, convertClaimsToJwtClaims(e.getClaims()));
		} catch (JwtException e) {
			throw new JwtTokenAuthenticationException(JWT_INVALID, e);
		}
	}
}
