package com.cafegory.auth.infrastructure.oauth2.dto;

import org.springframework.util.MultiValueMap;

public interface OAuth2TokenRequest {

	OAuth2Provider getProvider();

	MultiValueMap<String, String> getParameters();
}
