package fr.byob.aws.rest.v1x.resource;

import org.junit.BeforeClass;

import com.google.inject.Guice;
import com.google.inject.Injector;

import fr.byob.aws.commons.guice.LoggerModule;
import fr.byob.aws.dynamodb.dao.impl.DynamoDBModule;
import fr.byob.aws.rest.RESTJerseyServletModule;

public abstract class AbstractResourceTest {

	protected static Injector injector;
	
	@BeforeClass
	public static void beforeClass() {
		injector = Guice.createInjector(new LoggerModule(),
				new RESTJerseyServletModule(), new DynamoDBModule());
	}
}
