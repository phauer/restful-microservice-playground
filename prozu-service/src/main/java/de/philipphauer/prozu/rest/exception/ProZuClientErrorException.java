package de.philipphauer.prozu.rest.exception;

import javax.ws.rs.WebApplicationException;

import de.philipphauer.prozu.rest.responses.ErrorResponse;

@SuppressWarnings("serial")
// @ApplicationException
public class ProZuClientErrorException extends WebApplicationException {

	private long errorId;
	private String message;

	public ProZuClientErrorException(String message, long errorId) {
		this.message = message;
		this.errorId = errorId;
	}

	public ProZuClientErrorException(Throwable throwable, long errorId) {
		Throwable causeException = throwable.getCause();
		message = causeException != null ? causeException.getMessage() : throwable.getMessage();
		this.errorId = errorId;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public long getErrorId() {
		return errorId;
	}

	public ErrorResponse createErrorResponse() {
		return new ErrorResponse(getMessage(), errorId);
	}
}
