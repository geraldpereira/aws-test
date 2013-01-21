package fr.byob.aws.rest.exception;

import com.sun.jersey.api.client.ClientResponse.Status;

/**
 * IllegalRequestException REST exception
 * 
 * @author gpereira
 *
 */
public class IllegalRequestException extends RuntimeException {

	private static final long serialVersionUID = -7421895058638572651L;
	
	private final Status status;

	/**
	 * Constructor
	 * @param exception
	 */
	public IllegalRequestException(final Exception exception) {
		this(exception.getMessage());
	}

	private IllegalRequestException(final String message) {
		this(message, Status.BAD_REQUEST);
	}

	private IllegalRequestException(final String message, final Status status) {
		super(message);
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}
}