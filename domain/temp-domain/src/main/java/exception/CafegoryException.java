package exception;

import lombok.NonNull;

public class CafegoryException extends RuntimeException {
	private final ExceptionType exceptionType;

	public CafegoryException(@NonNull ExceptionType exceptionType) {
		this.exceptionType = exceptionType;
	}

	@Override
	public String getMessage() {
		return exceptionType.getErrorMessage();
	}

	public int getHttpStatus() {
		return exceptionType.getErrStatus();
	}

	public ExceptionType getExceptionType() {
		return exceptionType;
	}
}
