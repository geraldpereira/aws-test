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
	
	private static final int HTTP_400 = 400;

	private final Status status;
	private final int errorCode;

	/**
	 * Constructor
	 * @param exception
	 */
	public IllegalRequestException(final Exception exception) {
		this(exception.getMessage());
	}

	private IllegalRequestException(final String message) {
		this(message, Status.BAD_REQUEST, HTTP_400);
	}

	private IllegalRequestException(final String message, final Status status, final int errorCode) {
		super(message);
		this.status = status;
		this.errorCode = errorCode;
	}

	public Status getStatus() {
		return status;
	}

	public int getErrorCode() {
		return errorCode;
	}

}