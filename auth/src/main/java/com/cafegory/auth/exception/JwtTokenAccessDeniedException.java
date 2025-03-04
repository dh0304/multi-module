package com.cafegory.auth.exception;

import com.cafegory.domain.common.exception.ExceptionType;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class JwtTokenAccessDeniedException extends RuntimeException {

	private final ExceptionType exceptionType;

	public JwtTokenAccessDeniedException(@NonNull ExceptionType exceptionType) {
		this.exceptionType = exceptionType;
	}

	@Override
	public String getMessage() {
		return exceptionType.getErrorMessage();
	}

	public int getHttpStatus() {
		return exceptionType.getErrStatus();
	}
}
