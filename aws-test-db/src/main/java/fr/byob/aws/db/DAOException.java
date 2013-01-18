package fr.byob.aws.db;

/**
 * DAOException : issue related to the database
 * 
 * @author gpereira
 *
 */
public class DAOException extends Exception {
	
	/**
	 * Constructor
	 * @param message 
	 */
	public DAOException(final String message) {
		super(message);
	}
	
	/**
	 * Constructor
	 * @param cause
	 */
	public DAOException(final Throwable cause){
		super(cause);
	}

}
