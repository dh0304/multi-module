package com.cafegory.api.restdocs;

import com.cafegory.api.config.ApiDocsTest;
import com.cafegory.db.cafe.cafe.CafeEntity;
import com.cafegory.db.cafe.tag.CafeTagEntity;
import com.cafegory.db.member.MemberEntity;
import com.cafegory.db.study.tag.CafeStudyTagEntity;
import com.cafegory.domain.cafe.domain.CafeTagType;
import com.cafegory.domain.common.time.TimeUtil;
import com.cafegory.domain.study.domain.CafeStudyTagType;
import com.cafegory.domain.study.domain.MemberComms;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static com.cafegory.db.testfixtures.persister.cafe.CafeContextPersister.aCafe;
import static com.cafegory.db.testfixtures.persister.cafe.CafeTagPersister.aCafeTag;
import static com.cafegory.db.testfixtures.persister.member.MemberPersister.aMember;
import static com.cafegory.db.testfixtures.persister.study.StudyConextPersister.aStudy;
import static com.cafegory.db.testfixtures.persister.study.StudyTagPersister.aTag;



class CafeStudyControllerApiTest extends ApiDocsTest {

	@Autowired
	private TimeUtil timeUtil;


	 @Test
	 void create() {
		 //TODO 버전확인후 버전에 맞는 문법으로 교체
//	 	//given
//	 	CafeEntity cafe = cafeSaveHelper.saveCafeWith24For7();
//
//	 	LocalDateTime startDateTime = timeUtil.now().plusHours(2);
//	 	LocalDateTime endDateTime = startDateTime.plusHours(1);
//
//	 	Map<String, String> params = new HashMap<>();
//	 	params.put("name", "카페고리 스터디");
//	 	params.put("cafeId", String.valueOf(cafe.getId()));
//	 	params.put("startDateTime", startDateTime.toString());
//	 	params.put("endDateTime", endDateTime.toString());
//	 	params.put("memberComms", "WELCOME");
//	 	params.put("maxParticipants", String.valueOf(4));
//	 	params.put("introduction", "카페고리 스터디 소개글");
//
//	 	JwtToken jwtToken = memberSignupHelper.로그인_되어_있음();
//
//	 	RestAssured.given(spec).log().all()
//	 		.filter(RestAssuredRestDocumentationWrapper.document(
//	 				"카공 생성 API",
//	 				requestHeaders(
//	 					headerWithName("Authorization").description("JWT 액세스 토큰"))
//	 			)
//	 		)
//	 		.contentType(ContentType.JSON)
//	 		.header("Authorization", "Bearer " + jwtToken.getAccessToken())
//	 		.body(params)
//	 		.when()
//	 		.post("/cafe-studies")
//	 		.then().log().all()
//	 		.statusCode(200);
	 }

	@Test
	void searchCafeStudies() {
		//TODO 버전확인후 버전에 맞는 문법으로 교체

//		CafeTagEntity wifi = aCafeTag().withType(CafeTagType.WIFI).persist();
//		CafeTagEntity outlet = aCafeTag().withType(CafeTagType.OUTLET).persist();
//		CafeTagEntity comfortableSeating = aCafeTag().withType(CafeTagType.COMFORTABLE_SEATING).persist();
//
//		CafeEntity cafe1 = aCafe().includeKeywords("강남").includeTags(wifi, outlet).persistWith7daysFrom9To21();
//		CafeEntity cafe2 = aCafe().includeKeywords("강남")
//			.includeTags(wifi, outlet, comfortableSeating)
//			.persistWith24For7();
//
//		MemberEntity member = aMember().asCoordinator().persist();
//
//		CafeStudyTagEntity development = aTag().withType(CafeStudyTagType.DEVELOPMENT).persist();
//		CafeStudyTagEntity design = aTag().withType(CafeStudyTagType.DESIGN).persist();
//
//		aStudy().withCafe(cafe1).withMember(member).withMemberComms(MemberComms.WELCOME)
//			.withStudyPeriod(
//				timeUtil.localDateTime(2000, 1, 1, 12, 0, 0),
//				timeUtil.localDateTime(2000, 1, 1, 14, 0, 0)
//			)
//			.includeTags(development)
//			.persist();
//		aStudy().withCafe(cafe2).withMember(member).withMemberComms(MemberComms.WELCOME)
//			.withStudyPeriod(
//				timeUtil.localDateTime(2000, 1, 1, 14, 0, 0),
//				timeUtil.localDateTime(2000, 1, 1, 16, 0, 0)
//			)
//			.includeTags(development, design)
//			.persist();
//
//		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//		params.add("keyword", "강남");
//		params.add("date", "2000-01-01");
//		params.add("cafeStudyTag", CafeStudyTagType.DEVELOPMENT.toString());
//		params.add("cafeTags", CafeTagType.WIFI.toString());
//		params.add("cafeTags", CafeTagType.OUTLET.toString());
//		params.add("memberComms", MemberComms.WELCOME.toString());
//		params.add("page", "0");
//		params.add("sizePerPage", "10");

//		RestAssured.given(spec).log().all()
//			.filter(document(
//					"카공 목록 조회 API",
//					resource(
//                            ResourceSnippetParameters.builder()
//                                    .requestParameters(
//                                            parameterWithName("keyword").description("검색어"),
//                                            parameterWithName("date").description("필터링 날짜"),
//                                            parameterWithName("cafeStudyTag").description("카공 태그"),
//                                            parameterWithName("cafeTags").description("카페 태그 리스트, 여러개의 카공 태그를 넣을 수 있다."),
//                                            parameterWithName("memberComms").description("소통여부"),
//                                            parameterWithName("page").description("페이지 번호, 0부터 시작한다."),
//                                            parameterWithName("sizePerPage").description("한 페이지에 들어가는 컨텐츠 개수")
//                                    ).build()
//					)
//				)
//			)
//			.contentType(ContentType.JSON)
//			.params(params)
//			.when()
//			.get("/cafe-studies")
//			.then().log().all()
//			.statusCode(200);
	}

	 @Test
	 void deleteCafeStudy() {
		 //TODO 버전확인후 버전에 맞는 문법으로 교체
//	 	LocalDateTime startDateTime = timeUtil.localDateTime(2000, 1, 1, 1, 0, 0);
//	 	LocalDateTime endDateTime = timeUtil.localDateTime(2000, 1, 1, 2, 0, 0);
//
//	 	CafeEntity cafe = cafeSaveHelper.saveCafeWith24For7();
//	 	JwtToken jwtToken = memberSignupHelper.로그인_되어_있음();
//	 	MemberEntity coordinator = memberReader.read("test@gmail.com");
//	 	CafeStudyEntity cafeStudy = cafeStudySaveHelper.saveCafeStudy(cafe, coordinator, startDateTime, endDateTime);
//
//	 	RestAssured.given(spec).log().all()
//	 		.filter(RestAssuredRestDocumentationWrapper.document(
//	 			"카공 삭제 API",
//	 			requestHeaders(
//	 				headerWithName("Authorization").description("JWT 액세스 토큰")),
//	 			pathParameters(
//	 				parameterWithName("cafeStudyId").description("카공 ID")
//	 			))
//	 		)
//	 		.header("Authorization", "Bearer " + jwtToken.getAccessToken())
//	 		.contentType(ContentType.JSON)
//	 		.pathParam("cafeStudyId", cafeStudy.getId())
//	 		.when()
//	 		.delete("/cafe-studies/{cafeStudyId}")
//	 		.then().log().all()
//	 		.statusCode(200);
	 }
}
