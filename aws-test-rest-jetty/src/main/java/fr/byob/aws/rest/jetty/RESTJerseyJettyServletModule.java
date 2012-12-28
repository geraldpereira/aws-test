package fr.byob.aws.rest.jetty;

import java.util.HashMap;

import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import fr.byob.aws.rest.CORSHeadersFilter;


public class RESTJerseyJettyServletModule extends JerseyServletModule {

	@Override
	protected void configureServlets() {
		/* bind jackson converters for JAXB/JSON serialization */
		bind(MessageBodyReader.class).to(JacksonJsonProvider.class);
		bind(MessageBodyWriter.class).to(JacksonJsonProvider.class);

		final HashMap<String, String> initParams = new HashMap<>();

		//		 <init-param>
		//		               <param-name>com.sun.jersey.api.json.POJOMappingFeature</param-name>
		//		               <param-value>true</param-value>
		//		           </init-param>
		//		<init-param>
		//	    <param-name>com.sun.jersey.config.property.packages</param-name>
		//	    <param-value>
		//	        com.myapp.userservice; // semi-colon seperated
		//	        com.myapp.mappedexception
		//	    </param-value>
		//	</init-param>

		initParams.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
		initParams.put("com.sun.jersey.config.property.packages", "fr.byob.aws.rest.exception;fr.byob.aws.rest.v1x");

		this.serve("/api/*").with(GuiceContainer.class, initParams);
		this.filter("/*").through(CORSHeadersFilter.class);
	}
}
