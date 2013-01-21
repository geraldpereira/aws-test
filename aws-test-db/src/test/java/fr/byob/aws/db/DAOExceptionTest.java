package fr.byob.aws.db;

import static org.junit.Assert.*;

import org.junit.Test;

public class DAOExceptionTest {

	private static final String MESSAGE = "test"; 
	
	@Test
	public void testString() {
		final DAOException exception = new DAOException(MESSAGE);
		assertEquals(MESSAGE, exception.getMessage());
	}
	
	@Test
	public void testException() {
		final DAOException exception = new DAOException(new Exception(MESSAGE));
		assertEquals(MESSAGE, exception.getCause().getMessage());
	}

}
