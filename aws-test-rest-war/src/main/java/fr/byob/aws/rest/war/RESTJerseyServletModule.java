package fr.byob.aws.rest.war;

import java.util.HashMap;

import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import fr.byob.aws.rest.CORSHeadersFilter;

public class RESTJerseyServletModule extends JerseyServletModule {

	@Override
	protected void configureServlets() {
		/* bind jackson converters for JAXB/JSON serialization */
		this.bind(MessageBodyReader.class).to(JacksonJsonProvider.class);
		this.bind(MessageBodyWriter.class).to(JacksonJsonProvider.class);

		final HashMap<String, String> initParams = new HashMap<>();
		// <init-param>
		// <param-name>com.sun.jersey.api.json.POJOMappingFeature</param-name>
		// <param-value>true</param-value>
		// </init-param>

		initParams.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
		initParams.put("com.sun.jersey.config.property.packages", "fr.byob.aws.rest.exception;fr.byob.aws.rest.v1x");
		// http://stackoverflow.com/questions/8275064/google-app-engine-500-error
		initParams.put("com.sun.jersey.config.feature.DisableWADL", "true");

		this.serve("/api/*").with(GuiceContainer.class, initParams);
		this.filter("/*").through(CORSHeadersFilter.class);
	}
}
