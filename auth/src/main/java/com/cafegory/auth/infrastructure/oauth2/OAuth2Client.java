package com.cafegory.auth.infrastructure.oauth2;

import com.cafegory.auth.infrastructure.oauth2.dto.OAuth2Profile;
import com.cafegory.auth.infrastructure.oauth2.dto.OAuth2TokenRequest;

public interface OAuth2Client {

	OAuth2Profile fetchMemberProfile(OAuth2TokenRequest oAuth2TokenRequest);
}
