package com.cafegory.api.restdocs;

import com.cafegory.api.config.ApiDocsTest;
import com.cafegory.auth.domain.JwtToken;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document;


class AuthControllerApiTest extends ApiDocsTest {

	@Test
	void refresh() {
        //TODO 버전확인후 버전에 맞는 문법으로 교체
//		JwtToken jwtToken = memberSignupHelper.로그인_되어_있음();
//
//		RestAssured.given(spec).log().all()
//			.filter(document(
//				"액세스 토큰 재발급 API",
//				requestHeaders(
//					headerWithName("Authorization").description("JWT 액세스 토큰"),
//					headerWithName("Refresh-Token").description("JWT 리프레시 토큰, 리프레시 토큰 앞에는 Bearer을 붙이지 않는다.")
//				),
//				responseFields(
//					fieldWithPath("accessToken").description("JWT 액세스 토큰")
//				)
//			))
//			.contentType(ContentType.JSON)
//			.header("Authorization", "Bearer " + jwtToken.getAccessToken())
//			.header("Refresh-Token", jwtToken.getRefreshToken())
//			.when()
//			.post("/auth/refresh")
//			.then().log().all()
//			.statusCode(200);
	}
}
