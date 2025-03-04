package com.cafegory.api.exception;

import com.cafegory.auth.exception.JwtTokenAccessDeniedException;
import com.cafegory.auth.exception.JwtTokenAuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class JwtTokenExceptionHandler {

	@ExceptionHandler(JwtTokenAuthenticationException.class)
	public ResponseEntity<ErrorResponse> handle(JwtTokenAuthenticationException exception) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
		return ResponseEntity.status(exception.getHttpStatus()).body(errorResponse);
	}

	@ExceptionHandler(JwtTokenAccessDeniedException.class)
	public ResponseEntity<ErrorResponse> handle(JwtTokenAccessDeniedException exception) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
		return ResponseEntity.status(exception.getHttpStatus()).body(errorResponse);
	}
}
