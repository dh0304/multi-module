package com.cafegory.auth.infrastructure.oauth2.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
@RequiredArgsConstructor
public class NaverOAuth2TokenRequest implements OAuth2TokenRequest {
	@NonNull
	private final String code;
	@NonNull
	private final String state;

	@Override
	public OAuth2Provider getProvider() {
		return OAuth2Provider.NAVER;
	}

	@Override
	public MultiValueMap<String, String> getParameters() {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("code", code);
		map.add("state", state);
		return map;
	}
}
