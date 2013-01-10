package fr.byob.game.memeduel.server.rest;

import org.junit.BeforeClass;

import com.google.inject.Injector;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.ClientFilter;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.core.util.Base64;

/**
 * Begin by starting the gae dev server
 * 
 * @author Kojiro
 * 
 */
public class AbstractResourceTest {

	protected static WebResource webResource;

	protected static Injector tartiflette;

	@BeforeClass
	public static void beforeClass() throws Exception {
		// Init client
		final ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		final Client client = Client.create(clientConfig);

		client.addFilter(new ClientFilter() {
			@Override
			public ClientResponse handle(final ClientRequest cr) throws ClientHandlerException {
				final ClientResponse response = this.getNext().handle(cr);
				return response;
			}
		});
		// Init web resource
//			webResource = client.resource("http://localhost:8080/aws-test/api/v1.0");
		webResource = client.resource("http://aws-test.byob.fr/aws-test/api/v1.0");
//		webResource = client.resource("http://46.137.142.11/aws-test/api/v1.0");

		//		webResource = client.resource("http://localhost:10080/api/v1.0");

	}
}
