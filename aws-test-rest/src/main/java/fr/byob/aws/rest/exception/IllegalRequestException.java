package fr.byob.aws.rest.exception;

import com.sun.jersey.api.client.ClientResponse.Status;

import fr.byob.aws.db.DAOException;

public class IllegalRequestException extends RuntimeException {

	private static final long serialVersionUID = -7421895058638572651L;

	private Status status;
	private int errorCode;

	public IllegalRequestException(final DAOException exception) {
		this(exception.getMessage());
	}

	public IllegalRequestException(final String message) {
		this(message, Status.BAD_REQUEST, 400);
	}

	public IllegalRequestException(final String message, final Status status, final int errorCode) {
		super(message);
		this.status = status;
		this.errorCode = errorCode;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(final Status status) {
		this.status = status;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(final int errorCode) {
		this.errorCode = errorCode;
	}

}