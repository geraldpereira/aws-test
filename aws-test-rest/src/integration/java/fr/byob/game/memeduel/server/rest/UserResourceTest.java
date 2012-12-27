package fr.byob.game.memeduel.server.rest;

import javax.ws.rs.core.MediaType;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

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

import fr.byob.game.memeduel.server.rest.db.bean.User;

/**
 * Begin by starting the gae dev server (run as > Web application)
 * 
 * @author Kojiro
 * 
 */
public class UserResourceTest {

	private static WebResource webResource;

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
		// webResource = client.resource("http://localhost:8888/api/v1.0");
		webResource = client.resource("http://memeduel-server.appspot.com/api/v1.0");

	}

	@Test
	public void testUser() {
		final User user = new User("login", "password");
		user.setEmail("test@gmail.com");

		User returnedUser = webResource.path("/user/add").type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(User.class, user);

		Assert.assertEquals(user, returnedUser);

		user.setEmail("test2@gmail.com");
		user.setPassword("password2");

		returnedUser = webResource.path("/user/update").header("authorization", computeAuthHeader("login", "password")).type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(User.class, user);

		Assert.assertEquals(user, returnedUser);

		webResource.path("/user/delete/login").header("authorization", computeAuthHeader("login", "password2")).delete();
	}

	private final static String computeAuthHeader(final String login, final String password) {
		final byte[] pwd = Base64.encode(login + ":" + password);
		final String value = new String(pwd);
		return "Basic " + value;
	}

}
