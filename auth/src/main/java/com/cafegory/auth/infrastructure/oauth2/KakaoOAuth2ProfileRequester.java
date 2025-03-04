package com.cafegory.auth.infrastructure.oauth2;

import com.cafegory.auth.infrastructure.oauth2.dto.KakaoOAuth2Profile;
import com.cafegory.auth.infrastructure.oauth2.dto.OAuth2Profile;
import com.cafegory.auth.infrastructure.oauth2.dto.OAuth2Token;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public final class KakaoOAuth2ProfileRequester extends AbstractOAuth2ProfileRequester {
	private final RestTemplate restTemplate;
	@Value("${oauth.kakao.url.api}")
	private String apiUrl;

	protected String makeUrl() {
		return apiUrl + "/v2/user/me";
	}

	@Override
	protected MultiValueMap<String, String> makeBody() {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("property_keys", "[\"kakao_account.email\", \"kakao_account.profile\"]");
		return body;
	}

	@Override
	protected HttpHeaders makeHeader(OAuth2Token oAuth2Token) {
		String accessToken = oAuth2Token.getAccessToken();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		httpHeaders.set("Authorization", "Bearer " + accessToken);
		return httpHeaders;
	}

	@Override
	protected ResponseEntity<? extends OAuth2Profile> callProfileApi(String url, HttpHeaders httpHeaders,
																	 MultiValueMap<String, String> body) {
		HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);
		return restTemplate.postForEntity(url, request, KakaoOAuth2Profile.class);
	}

	@Override
	protected void validateProfileApiResponse(ResponseEntity<? extends OAuth2Profile> oAuth2ProfileResponse) {
		if (oAuth2ProfileResponse.getStatusCode() != HttpStatus.OK) {
			throw new RuntimeException("카카오에서 거부했습니다.");
		}
	}
}
