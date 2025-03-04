package com.cafegory.auth.infrastructure.oauth2;

import com.cafegory.auth.infrastructure.oauth2.dto.OAuth2Token;
import com.cafegory.auth.infrastructure.oauth2.dto.OAuth2TokenRequest;

public interface OAuth2TokenRequester {

	OAuth2Token getToken(OAuth2TokenRequest oAuth2TokenRequest);
}
