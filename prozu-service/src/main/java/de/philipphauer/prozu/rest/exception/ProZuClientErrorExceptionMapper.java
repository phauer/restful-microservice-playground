package de.philipphauer.prozu.rest.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import de.philipphauer.prozu.rest.responses.ErrorResponse;
import de.philipphauer.prozu.rest.util.MediaTypeWithCharset;

@Provider
public class ProZuClientErrorExceptionMapper implements ExceptionMapper<ProZuClientErrorException> {

	@Override
	public Response toResponse(ProZuClientErrorException exception) {
		ErrorResponse errorResponse = exception.createErrorResponse();
		return Response.status(Status.BAD_REQUEST).type(MediaTypeWithCharset.APPLICATION_JSON_UTF8)
				.entity(errorResponse).build();
	}

}
