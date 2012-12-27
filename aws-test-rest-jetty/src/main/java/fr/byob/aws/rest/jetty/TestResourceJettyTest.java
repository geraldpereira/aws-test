package fr.byob.gae.server.rest.jetty;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.ws.rs.core.MediaType;

import junit.framework.Assert;

import org.junit.AfterClass;
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

import fr.byob.gae.server.rest.model.v1x.bean.TestRequest;
import fr.byob.gae.server.rest.model.v1x.bean.TestResult;

public class TestResourceJettyTest {

	private static String authorization;
	private static WebResource webResource;
	private static Launcher launcher;

	@BeforeClass
	public static void beforeClass() throws Exception {
		launcher = new Launcher();

		// Init http auth header
		final byte[] pwd = Base64.encode("rest-user:niancat");
		final String value = new String(pwd);
		authorization = "Basic " + value;

		// Init client
		final ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		final Client client = Client.create(clientConfig);

		client.addFilter(new ClientFilter() {
			@Override
			public ClientResponse handle(final ClientRequest cr) throws ClientHandlerException {
				final ClientResponse response = getNext().handle(cr);
				return response;
			}
		});
		//		cloud-rest/api/
		// Init web resource
		webResource = client.resource("http://localhost:10080/v1.0");
	}

	@AfterClass
	public static void afterClass() throws Exception {
		launcher.stop();
	}

	@Test
	public void testTest() {
		final TestRequest request = new TestRequest();
		request.setValue("niak niak");

		final TestResult result = webResource.path("test/test")
				.header("authorization", authorization)
				.type(MediaType.APPLICATION_JSON_TYPE)
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.post(TestResult.class,
						request);

		Assert.assertEquals("niak niak tested!", result.getValue());
	}

	@Test
	public void testCar() {
		try {
			// open url connection
			URL url = new URL("http://localhost:10080/v1.0/test/test");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			// set up url connection to get retrieve information back
			con.setRequestMethod("GET");
			con.setDoInput(true);

			con.setRequestProperty("authorization", authorization);
			String result = readFully(new InputStreamReader(
					con.getInputStream()));
			System.out.println(result);
			con.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static final int BUF_SIZE = 4096;

	private String readFully(Reader reader) throws IOException {
		StringBuffer result = new StringBuffer();
		char[] buf = new char[BUF_SIZE];
		int len = 0;
		while (-1 != (len = reader.read(buf))) {
			result.append(buf, 0, len);
		}
		return result.toString();
	}

}
