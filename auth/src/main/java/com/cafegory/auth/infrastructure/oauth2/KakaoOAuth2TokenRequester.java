package com.cafegory.auth.infrastructure.oauth2;

import com.cafegory.auth.infrastructure.oauth2.dto.KakaoOAuth2Token;
import com.cafegory.auth.infrastructure.oauth2.dto.OAuth2Provider;
import com.cafegory.auth.infrastructure.oauth2.dto.OAuth2Token;
import com.cafegory.auth.infrastructure.oauth2.dto.OAuth2TokenRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class KakaoOAuth2TokenRequester extends AbstractOAuth2TokenRequester {

	private final RestTemplate restTemplate;
	private static final String GRANT_TYPE = "authorization_code";

	@Value("${oauth.kakao.url.auth}")
	private String authUrl;
	@Value("${oauth.kakao.client-id}")
	private String clientId;
	@Value("${oauth.kakao.client-secret}")
	private String clientSecret;

	@Override
	protected OAuth2Provider getOAuth2Provider() {
		return OAuth2Provider.KAKAO;
	}

	@Override
	protected String makeUrl() {
		return authUrl + "/oauth/token";
	}

	@Override
	protected HttpHeaders makeHeader() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		return httpHeaders;
	}

	@Override
	protected MultiValueMap<String, String> makeBody(OAuth2TokenRequest oAuth2TokenRequest) {
		MultiValueMap<String, String> httpBody = oAuth2TokenRequest.getParameters();
		httpBody.add("grant_type", GRANT_TYPE);
		httpBody.add("client_id", clientId);
		httpBody.add("client_secret", clientSecret);
		return httpBody;
	}

	@Override
	protected ResponseEntity<? extends OAuth2Token> callTokenApi(String url, HttpHeaders httpHeaders,
																 MultiValueMap<String, String> httpBody) {
		HttpEntity<?> request = new HttpEntity<>(httpBody, httpHeaders);
		return restTemplate.postForEntity(url, request, KakaoOAuth2Token.class);
	}
}
