package com.cafegory.auth.infrastructure.oauth2;

import com.cafegory.auth.infrastructure.oauth2.dto.OAuth2Provider;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class OAuth2StrategyFactory {

	private final Map<OAuth2Provider, OAuth2TokenRequester> tokenRequesters;
	private final Map<OAuth2Provider, OAuth2ProfileRequester> profileRequesters;

	public OAuth2TokenRequester getTokenRequester(OAuth2Provider provider) {
		return tokenRequesters.get(provider);
	}

	public OAuth2ProfileRequester getProfileRequester(OAuth2Provider provider) {
		return profileRequesters.get(provider);
	}
}
