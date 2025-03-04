package com.cafegory.api.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JwtErrorResponse {

	private final String errorMessage;
}
