package com.cafegory.auth.infrastructure.oauth2;

import com.cafegory.auth.infrastructure.oauth2.dto.OAuth2Profile;
import com.cafegory.auth.infrastructure.oauth2.dto.OAuth2Provider;
import com.cafegory.auth.infrastructure.oauth2.dto.OAuth2Token;
import com.cafegory.auth.infrastructure.oauth2.dto.OAuth2TokenRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OAuth2HandlerImpl implements OAuth2Client {

	private final OAuth2StrategyFactory oAuth2StrategyFactory;

	@Override
	public OAuth2Profile fetchMemberProfile(OAuth2TokenRequest oAuth2TokenRequest) {
		OAuth2Provider provider = oAuth2TokenRequest.getProvider();

		OAuth2TokenRequester tokenRequester = oAuth2StrategyFactory.getTokenRequester(provider);
		OAuth2Token token = tokenRequester.getToken(oAuth2TokenRequest);

		OAuth2ProfileRequester profileRequester = oAuth2StrategyFactory.getProfileRequester(provider);
		return profileRequester.getOAuth2Profile(token);
	}
}
