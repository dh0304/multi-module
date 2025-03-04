package com.cafegory.auth.infrastructure.oauth2;

import com.cafegory.auth.infrastructure.oauth2.dto.OAuth2Profile;
import com.cafegory.auth.infrastructure.oauth2.dto.OAuth2Token;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public abstract class AbstractOAuth2ProfileRequester implements OAuth2ProfileRequester {
	@Override
	public final OAuth2Profile getOAuth2Profile(OAuth2Token oAuth2Token) {
		String url = makeUrl();
		HttpHeaders httpHeaders = makeHeader(oAuth2Token);
		MultiValueMap<String, String> body = makeBody();
		ResponseEntity<? extends OAuth2Profile> oAuth2ProfileResponse = callProfileApi(url, httpHeaders, body);
		validateProfileApiResponse(oAuth2ProfileResponse);
		return oAuth2ProfileResponse.getBody();
	}

	protected abstract String makeUrl();

	protected abstract HttpHeaders makeHeader(OAuth2Token oAuth2Token);

	protected abstract MultiValueMap<String, String> makeBody();

	protected abstract ResponseEntity<? extends OAuth2Profile> callProfileApi(String url, HttpHeaders httpHeaders,
		MultiValueMap<String, String> body);

	protected abstract void validateProfileApiResponse(ResponseEntity<? extends OAuth2Profile> oAuth2ProfileResponse);

}
