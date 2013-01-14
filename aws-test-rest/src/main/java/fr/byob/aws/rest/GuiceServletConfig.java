package fr.byob.aws.rest;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

import fr.byob.aws.commons.guice.LoggerModule;
import fr.byob.aws.dynamodb.dao.impl.DynamoDBModule;
import fr.byob.aws.rest.RESTJerseyServletModule;
import fr.byob.aws.rest.ResourcesModule;

/**
 * 
 * @author gpereira
 *
 */
public class GuiceServletConfig extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new LoggerModule(),new DynamoDBModule(),new ResourcesModule(), new RESTJerseyServletModule());
	}
}