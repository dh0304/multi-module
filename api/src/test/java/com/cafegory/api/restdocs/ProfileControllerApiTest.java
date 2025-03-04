package com.cafegory.api.restdocs;

import com.cafegory.api.config.ApiDocsTest;
import com.cafegory.auth.domain.JwtToken;
import com.cafegory.db.cafe.cafe.CafeEntity;
import com.cafegory.db.cafe.tag.CafeTagEntity;
import com.cafegory.db.member.MemberEntity;
import com.cafegory.db.member.MemberJpaRepository;
import com.cafegory.domain.cafe.domain.CafeTagType;
import com.cafegory.domain.member.implement.MemberReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.cafegory.db.testfixtures.persister.cafe.CafeContextPersister.aCafe;
import static com.cafegory.db.testfixtures.persister.cafe.CafeTagPersister.aCafeTag;
import static com.cafegory.db.testfixtures.persister.study.ReviewContextPersister.aReview;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document;

class ProfileControllerApiTest extends ApiDocsTest {

	@Autowired
	private MemberReader memberReader;
	@Autowired
	private MemberJpaRepository memberJpaRepository;

	@Test
	void welcome() {
        //TODO 버전확인후 버전에 맞는 문법으로 교체
//		JwtToken jwtToken = memberSignupHelper.로그인_되어_있음();
//
//		RestAssured.given(spec).log().all()
//			.filter(document(
//					"회원가입 환영 페이지 API",
//					requestHeaders(
//						headerWithName("Authorization").description("JWT 액세스 토큰")
//					),
//					responseFields(
//						fieldWithPath("nickname").description("회원 닉네임"),
//						fieldWithPath("profileUrl").description("회원 프로필 URL")
//					)
//				)
//			)
//			.contentType(ContentType.JSON)
//			.header("Authorization", "Bearer " + jwtToken.getAccessToken())
//			.when()
//			.get("/profile/welcome")
//			.then().log().all()
//			.statusCode(200);
	}

	@Test
	@DisplayName("마이페이지 조회 API")
	void mypage() {
        //TODO 버전확인후 버전에 맞는 문법으로 교체
//		CafeEntity cafe1 = aCafe().persistWith24For7();
//		CafeEntity cafe2 = aCafe().persistWith24For7();
//
//		JwtToken jwtToken = memberSignupHelper.로그인_되어_있음();
//		MemberEntity member = memberJpaRepository.findByEmail("test@gmail.com").get();
//
//		CafeTagEntity wifi = aCafeTag().withType(CafeTagType.WIFI).persist();
//		CafeTagEntity outlet = aCafeTag().withType(CafeTagType.OUTLET).persist();
//
//		aReview().withCafe(cafe1).withMember(member).includeTags(wifi).save();
//		aReview().withCafe(cafe2).withMember(member).includeTags(wifi, outlet).save();
//
//		RestAssured.given(spec).log().all()
//			.filter(document(
//					"마이 페이지 조회 API",
//					requestHeaders(
//						headerWithName("Authorization").description("JWT 액세스 토큰")
//					)
//				)
//			)
//			.contentType(ContentType.JSON)
//			.header("Authorization", "Bearer " + jwtToken.getAccessToken())
//			.when()
//			.get("/profile/mypage")
//			.then().log().all()
//			.statusCode(200);
	}
}
