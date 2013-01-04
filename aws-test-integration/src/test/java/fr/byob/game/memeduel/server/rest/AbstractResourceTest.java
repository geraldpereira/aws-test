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

	protected final static String LOGIN = "loginUser";
	protected final static String PASSWORD = "password";

	protected static WebResource webResource;

	// protected final LocalServiceTestHelper helper = new
	// LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

	protected static Injector tartiflette;

	// @Before
	// public void before() {
	// helper.setUp();
	// }
	//
	// @After
	// public void after() {
	// helper.tearDown();
	// }

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
//		webResource = client.resource("http://localhost:8080/api/v1.0");
		webResource = client.resource("http://aws-test.byob.fr/aws-test/api/v1.0");

		//		webResource = client.resource("http://localhost:10080/api/v1.0");
		
		
		// webResource =
		// client.resource("http://memeduel-server.appspot.com/api/v1.0");

	}

	protected final static String computeAuthHeader(final String login, final String password) {
		final byte[] pwd = Base64.encode(login + ":" + password);
		final String value = new String(pwd);
		return "Basic " + value;
	}

}
