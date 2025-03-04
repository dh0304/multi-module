package com.cafegory.api.exception;

import com.cafegory.domain.common.exception.CafegoryException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
@Slf4j
public class CafegoryExceptionHandler {
	@ExceptionHandler(CafegoryException.class)
	public ResponseEntity<ErrorResponse> handle(CafegoryException exception) {
		PrintWriter printWriter = new PrintWriter(new StringWriter());
		exception.printStackTrace(printWriter);
		ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
		return ResponseEntity.status(exception.getHttpStatus()).body(errorResponse);
	}
}
