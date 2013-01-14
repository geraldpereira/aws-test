package fr.byob.aws.rest.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


/**
 * Maps an IllegalRequestException to an HTTP response
 * 
 * @author gpereira
 *
 */
@Provider
public class RestExceptionMapper implements ExceptionMapper<IllegalRequestException> {

	@Override
	public Response toResponse(final IllegalRequestException e) {
		return Response.status(e.getStatus()).entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
	}

}