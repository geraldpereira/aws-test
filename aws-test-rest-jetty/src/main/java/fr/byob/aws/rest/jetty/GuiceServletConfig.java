package fr.byob.gae.server.rest.jetty;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

import fr.byob.gae.server.commons.guice.LoggerModule;
import fr.byob.gae.server.rest.db.DAOModule;

public class GuiceServletConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(
        		new LoggerModule(),
				new RESTJerseyJettyServletModule()
				, new DAOModule()
				);
    }
}