package fr.byob.aws.rest.exception;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;

import org.junit.Test;

import com.sun.jersey.api.client.ClientResponse.Status;

public class ExceptionTest {

	@Test
	public void test() {
		final IllegalRequestException exception = new IllegalRequestException(new Exception("test"));
		assertEquals(Status.BAD_REQUEST, exception.getStatus());
		assertEquals("test", exception.getMessage());
		
		final RestExceptionMapper mapper = new RestExceptionMapper();
		final Response response = mapper.toResponse(exception);
		assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
		assertEquals("test", response.getEntity().toString());
	}

}
