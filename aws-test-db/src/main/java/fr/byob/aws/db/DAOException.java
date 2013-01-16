package fr.byob.aws.db;

public class DAOException extends Exception {
	public DAOException(final String message) {
		super(message);
	}
	
	public DAOException(final Throwable cause){
		super(cause);
	}

}
