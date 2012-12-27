package fr.byob.gae.server.rest.jetty;

import java.util.HashMap;

import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import fr.byob.gae.server.rest.AuthenticationFilter;
import fr.byob.gae.server.rest.CORSHeadersFilter;
import fr.byob.gae.server.rest.v1x.manager.CarManager;
import fr.byob.gae.server.rest.v1x.manager.TestManager;
import fr.byob.gae.server.rest.v1x.manager.impl.CarManagerImpl;
import fr.byob.gae.server.rest.v1x.manager.impl.TestManagerImpl;


public class RESTJerseyJettyServletModule extends JerseyServletModule {

	@Override
	protected void configureServlets() {
		bind(TestManager.class).to(TestManagerImpl.class);
		bind(CarManager.class).to(CarManagerImpl.class);

		/* bind the REST resources */
		bind(fr.byob.gae.server.rest.v1x.resource.TestResource.class);
		bind(fr.byob.gae.server.rest.v1x.resource.CarResource.class);

		/* bind jackson converters for JAXB/JSON serialization */
		bind(MessageBodyReader.class).to(JacksonJsonProvider.class);
		bind(MessageBodyWriter.class).to(JacksonJsonProvider.class);

		final HashMap<String, String> initParams = new HashMap<String, String>();

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
		initParams.put("com.sun.jersey.config.property.packages",
						"fr.byob.gae.server.rest.model.exception;fr.byob.gae.server.rest.v1x");
		serve("/*").with(GuiceContainer.class, initParams);
		filter("/*").through(CORSHeadersFilter.class);
		filter("/*").through(AuthenticationFilter.class);
	}
}
