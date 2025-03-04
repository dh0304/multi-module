package com.cafegory.api.restdocs;

import com.cafegory.api.config.ApiDocsTest;
import com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

class LoginControllerApiTest extends ApiDocsTest {

    @Test
    void refresh() {
        //TODO 버전이 바뀌었으니 버전에 맞는 문법 적용 필요
//        RestAssured.given(spec).log().all()
//            .filter(document(
//                    "카카오 로그인 API",
//                    requestParameters(
//                        parameterWithName("code").description("카카오 인증 code")
//                    ),
//                    responseFields(
//                        fieldWithPath("accessToken").description("JWT 액세스 토큰"),
//                        fieldWithPath("refreshToken").description("JWT 리프레시 토큰")
//                    )
//                )
//            )
//            .contentType(ContentType.JSON)
//            .param("code", "kakaoCode")
//            .when()
//            .get("/login/kakao")
//            .then().log().all()
//            .statusCode(200);
    }
}
