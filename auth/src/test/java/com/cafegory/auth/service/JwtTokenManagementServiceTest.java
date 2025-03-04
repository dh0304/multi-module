//package com.cafegory.auth.service;
//
//import com.cafegory.auth.exception.JwtTokenAuthenticationException;
//import com.cafegory.db.member.MemberEntity;
//import com.cafegory.domain.common.exception.ExceptionType;
//import com.cafegory.domain.config.ServiceTest;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//
//import java.util.Map;
//
//import static com.cafegory.auth.domain.TokenClaims.*;
//import static com.cafegory.auth.testfixtures.builder.JwtTokenBuilder.aToken;
//import static com.cafegory.db.testfixtures.persister.member.MemberPersister.aMember;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
//
//class JwtTokenManagementServiceTest extends ServiceTest {
//
//	@Value(("${jwt.secret}"))
//	private String testSecret;
//
//	@Autowired
//	private JwtTokenManagementService sut;
//
//	@Test
//	@DisplayName("만료되지 않은 액세스 토큰과 만료되지 않은 리프레시 토큰을 검증한다.")
//	void verify_access_and_refresh_token() {
//		//given
//		MemberEntity member = MemberPersister.aMember().persist();
//
//		String accessToken = aToken().expiresInOneHour()
//			.withClaims(
//				Map.of(TOKEN_TYPE.getValue(), ACCESS_TOKEN.getValue(),
//					SUBJECT.getValue(), String.valueOf(member.getId()))
//			)
//			.withKey(testSecret).build();
//		String refreshToken = aToken().expiresInOneHour()
//			.withClaims(
//				Map.of(
//					TOKEN_TYPE.getValue(), REFRESH_TOKEN.getValue(),
//					SUBJECT.getValue(), String.valueOf(member.getId())))
//			.withKey(testSecret).build();
//		//when & then
//		Assertions.assertDoesNotThrow(() -> sut.verifyAndRefreshAccessToken(accessToken, refreshToken));
//	}
//
//	@Test
//	@DisplayName("만료된 액세스 토큰과 만료되지 않은 리프레시 토큰을 검증한다.")
//	void verify_expired_access_and_unexpired_refresh_token() {
//		//given
//		MemberEntity member = MemberPersister.aMember().persist();
//
//		String accessToken = aToken().expired()
//			.withClaims(
//				Map.of(TOKEN_TYPE.getValue(), ACCESS_TOKEN.getValue(),
//					SUBJECT.getValue(), String.valueOf(member.getId()))
//			)
//			.withKey(testSecret).build();
//		String refreshToken = aToken().expiresInOneHour()
//			.withClaims(
//				Map.of(
//					TOKEN_TYPE.getValue(), REFRESH_TOKEN.getValue(),
//					SUBJECT.getValue(), String.valueOf(member.getId())))
//			.withKey(testSecret).build();
//		//when & then
//		Assertions.assertDoesNotThrow(() -> sut.verifyAndRefreshAccessToken(accessToken, refreshToken));
//	}
//
//	@Test
//	@DisplayName("만료된 액세스 토큰과 만료된 리프레시 토큰을 검증한다.")
//	void verify_expired_access_and_expired_refresh_token() {
//		//given
//		MemberEntity member = MemberPersister.aMember().persist();
//
//		String accessToken = aToken().expired()
//			.withClaims(
//				Map.of(TOKEN_TYPE.getValue(), ACCESS_TOKEN.getValue(),
//					SUBJECT.getValue(), String.valueOf(member.getId()))
//			)
//			.withKey(testSecret).build();
//		String refreshToken = aToken().expired()
//			.withClaims(
//				Map.of(
//					TOKEN_TYPE.getValue(), REFRESH_TOKEN.getValue(),
//					SUBJECT.getValue(), String.valueOf(member.getId())))
//			.withKey(testSecret).build();
//		//when & then
//		assertThatThrownBy(() -> sut.verifyAndRefreshAccessToken(accessToken, refreshToken))
//			.isInstanceOf(JwtTokenAuthenticationException.class)
//			.hasMessage(ExceptionType.JWT_EXPIRED.getErrorMessage());
//	}
//
//	@Test
//	@DisplayName("만료되지 않은 액세스 토큰과 만료된 리프레시 토큰을 검증한다.")
//	void verify_unexpired_access_and_expired_refresh_token() {
//		//given
//		MemberEntity member = MemberPersister.aMember().persist();
//
//		String accessToken = aToken().expiresInOneHour()
//			.withClaims(
//				Map.of(TOKEN_TYPE.getValue(), ACCESS_TOKEN.getValue(),
//					SUBJECT.getValue(), String.valueOf(member.getId()))
//			)
//			.withKey(testSecret).build();
//		String refreshToken = aToken().expired()
//			.withClaims(
//				Map.of(
//					TOKEN_TYPE.getValue(), REFRESH_TOKEN.getValue(),
//					SUBJECT.getValue(), String.valueOf(member.getId())))
//			.withKey(testSecret).build();
//		//when & then
//		assertThatThrownBy(() -> sut.verifyAndRefreshAccessToken(accessToken, refreshToken))
//			.isInstanceOf(JwtTokenAuthenticationException.class)
//			.hasMessage(ExceptionType.JWT_EXPIRED.getErrorMessage());
//	}
//}
