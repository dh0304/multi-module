package com.cafegory.auth.infrastructure.oauth2;

import com.cafegory.auth.infrastructure.oauth2.dto.NaverOAuth2Profile;
import com.cafegory.auth.infrastructure.oauth2.dto.OAuth2Profile;
import com.cafegory.auth.infrastructure.oauth2.dto.OAuth2Token;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public final class NaverOAuth2ProfileRequester extends AbstractOAuth2ProfileRequester {
	private final RestTemplate restTemplate;
	@Value("${oauth.naver.url.api}")
	private String apiUrl;

	@Override
	protected String makeUrl() {
		return apiUrl + "/v1/nid/me";
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
	protected MultiValueMap<String, String> makeBody() {
		return new LinkedMultiValueMap<>();
	}

	@Override
	protected ResponseEntity<? extends OAuth2Profile> callProfileApi(String url, HttpHeaders httpHeaders,
																	 MultiValueMap<String, String> body) {
		HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);
		return restTemplate.postForEntity(url, request, NaverOAuth2Profile.class);
	}

	@Override
	protected void validateProfileApiResponse(ResponseEntity<? extends OAuth2Profile> oAuth2ProfileResponse) {
		if (oAuth2ProfileResponse.getStatusCode() != HttpStatus.OK) {
			throw new RuntimeException("네이버에서 거부했습니다.");
		}
	}
}
