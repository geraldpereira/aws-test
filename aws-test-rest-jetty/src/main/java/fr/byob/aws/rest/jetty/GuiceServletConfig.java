package fr.byob.aws.rest.jetty;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

import fr.byob.aws.commons.guice.LoggerModule;
import fr.byob.aws.dynamodb.dao.impl.DynamoDBModule;

public class GuiceServletConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new LoggerModule(),
				new RESTJerseyJettyServletModule()
				, new DynamoDBModule()
				);
    }
}