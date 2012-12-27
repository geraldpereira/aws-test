package fr.byob.game.memeduel.server.rest.test;

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
 * Used to manually send REST requests
 * 
 * @author gpereira
 * 
 */
public class TestMain {

	private static String authorization;
	private static WebResource webResource;

	static {
		// Init http auth header
		final byte[] pwd = Base64.encode("rest-user:niancat");
		final String value = new String(pwd);
		authorization = "Basic " + value;

		// Init client
		final ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
				Boolean.TRUE);
		final Client client = Client.create(clientConfig);

		client.addFilter(new ClientFilter() {
			@Override
			public ClientResponse handle(final ClientRequest cr)
					throws ClientHandlerException {
				final ClientResponse response = getNext().handle(cr);
				return response;
			}
		});
		// cloud-rest/api/
		// Init web resource
		// webResource = client.resource("http://localhost:8888/api/v1.0");
		// webResource = client
		// .resource("http://4.gae-server-rest.appspot.com/api/v1.0");

		webResource = client
.resource("https://apps.neotys.com/neotyscloud/faces/login.xhtml");

	}

	public static void main(final String[] args) {
	}

	public static void main1(final String[] args) {

		// final Car savedCar = webResource.path("car/get/1")
		// // .header("authorization", authorization)
		// .accept(MediaType.APPLICATION_JSON_TYPE).get(Car.class);
		//
		// System.out.println(savedCar);

	}

	public static void main2(final String[] args) {

		// final TestRequest request = new TestRequest();
		// request.setValue("niak niak");
		//
		// final TestResult result = webResource.path("test/test")
		// // .header("authorization", authorization)
		// .type(MediaType.APPLICATION_JSON_TYPE)
		// .accept(MediaType.APPLICATION_JSON_TYPE)
		// .post(TestResult.class, request);
		//
		// System.out.println(result);

		// final Car newCar = new Car("test", 15);
		// newCar.setDoNotPersist("test2");
		//
		// final Car savedCar = webResource
		// .path("car/add")
		// .header("authorization", authorization)
		// .type(MediaType.APPLICATION_JSON_TYPE)
		// .accept(MediaType.APPLICATION_JSON_TYPE)
		// .post(Car.class, newCar);
		//
		// System.out.println(savedCar);

	}

}
