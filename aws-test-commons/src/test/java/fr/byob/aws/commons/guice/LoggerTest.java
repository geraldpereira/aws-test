package fr.byob.aws.commons.guice;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class LoggerTest {

	@Test
	public void testLogger() {
		final Injector injector = Guice.createInjector(new LoggerModule(), new AbstractModule() {
			@Override
			protected void configure() {
				bind(LoggerContainer.class);
			}
		});
		
		final LoggerContainer container = injector.getInstance(LoggerContainer.class);
		assertNotNull(container.getLog());
		assertNull(container.getLogNoAnnotation());
		assertNull(container.getLog4J());
	}

	
	private final static class LoggerContainer{
		@InjectLogger
		private Logger log;

		private Logger logNoAnnotation;
		
		private org.apache.log4j.Logger log4J;
		
		public Logger getLog() {
			return log;
		}
		
		public org.apache.log4j.Logger getLog4J() {
			return log4J;
		}
		
		public Logger getLogNoAnnotation() {
			return logNoAnnotation;
		}
	}
}
