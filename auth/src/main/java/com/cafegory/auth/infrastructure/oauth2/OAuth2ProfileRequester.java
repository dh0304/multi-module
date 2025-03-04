package com.cafegory.auth.infrastructure.oauth2;

import com.cafegory.auth.infrastructure.oauth2.dto.OAuth2Profile;
import com.cafegory.auth.infrastructure.oauth2.dto.OAuth2Token;

public interface OAuth2ProfileRequester {

	OAuth2Profile getOAuth2Profile(OAuth2Token oAuth2Token);
}
