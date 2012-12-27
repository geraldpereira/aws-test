package fr.byob.aws.rest.exception;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class RestExceptionMapper implements ExceptionMapper<IllegalRequestException> {

	@Context
	private HttpHeaders headers;

	@Override
	public Response toResponse(final IllegalRequestException e) {
		return Response.status(e.getStatus()).entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
	}

	private static ExceptionEntity newExceptionEntity(
			final IllegalRequestException e) {
		return new ExceptionEntity(e.getMessage(), e.getStatus().toString(), e.getErrorCode());
	}
}