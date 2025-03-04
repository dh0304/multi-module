package com.cafegory.api.restdocs;

import com.cafegory.api.config.ApiDocsTest;
import com.cafegory.auth.domain.JwtToken;
import com.cafegory.db.cafe.cafe.CafeEntity;
import com.cafegory.db.member.MemberEntity;
import com.cafegory.db.study.study.CafeStudyEntity;
import com.cafegory.domain.common.time.TimeUtil;
import com.cafegory.domain.study.domain.MemberComms;
import com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

import static com.cafegory.db.testfixtures.persister.cafe.CafeContextPersister.aCafe;
import static com.cafegory.db.testfixtures.persister.member.MemberPersister.aMember;
import static com.cafegory.db.testfixtures.persister.study.StudyConextPersister.aStudy;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document;

class QnaApiTest extends ApiDocsTest {

	@Autowired
	private TimeUtil timeUtil;

	@Test
	@DisplayName("카공 Qna 댓글 등록 API")
	void leaveComment() {
		//TODO 버전확인후 버전에 맞는 문법으로 교체
//		CafeEntity cafe = aCafe().persistWith24For7();
//		MemberEntity coordinator = aMember().asCoordinator().persist();
//
//		CafeStudyEntity study = aStudy().withCafe(cafe).withMember(coordinator)
//			.withStudyPeriod(
//				timeUtil.localDateTime(2000, 1, 1, 12, 0, 0),
//				timeUtil.localDateTime(2000, 1, 1, 14, 0, 0)
//			)
//			.withMemberComms(MemberComms.WELCOME)
//			.persist();
//
//		Map<String, String> params = new HashMap<>();
//		params.put("content", "댓글 내용");
//		params.put("cafeStudyId", String.valueOf(study.getId()));
//
//		JwtToken jwtToken = memberSignupHelper.로그인_되어_있음();
//
//		RestAssured.given(spec).log().all()
//			.filter(document(
//					"카페 Qna 댓글 등록 API",
//					requestHeaders(
//						headerWithName("Authorization").description("JWT 액세스 토큰")
//					),
//					requestFields(
//						fieldWithPath("content").description("댓글 내용"),
//						fieldWithPath("cafeStudyId").description("카공 ID")
//					),
//					responseFields(
//						fieldWithPath("commentInfo.id").description("댓글 ID"),
//						fieldWithPath("commentInfo.content").description("댓글 내용"),
//						fieldWithPath("commentInfo.createdDate").description("댓글 작성일"),
//						fieldWithPath("writerInfo.id").description("작성자 ID"),
//						fieldWithPath("writerInfo.nickname").description("작성자 닉네임")
//					)
//				)
//			)
//			.contentType(ContentType.JSON)
//			.header("Authorization", "Bearer " + jwtToken.getAccessToken())
//			.body(params)
//			.when()
//			.post("/qna/comments")
//			.then().log().all()
//			.statusCode(200);
	}
}