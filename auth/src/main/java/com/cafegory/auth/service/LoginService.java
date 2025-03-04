package com.cafegory.auth.service;

import com.cafegory.auth.domain.JwtToken;
import com.cafegory.auth.infrastructure.oauth2.dto.OAuth2TokenRequest;

public interface LoginService {

	JwtToken socialLogin(OAuth2TokenRequest oAuth2TokenRequest);
}
