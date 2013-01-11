package fr.byob.game.memeduel.server.rest;

import java.net.Socket;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509KeyManager;
import javax.net.ssl.X509TrustManager;

import org.junit.BeforeClass;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.ClientFilter;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.client.urlconnection.HTTPSProperties;

/**
 * Begin by starting the gae dev server
 * 
 * @author Kojiro
 * 
 */
public class AbstractResourceTest {

	protected static WebResource webResource;

	@BeforeClass
	public static void beforeClass() throws Exception {
		// Init client
		final ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

		
		TrustManager[] trustAllCerts = new TrustManager[]{
		        new X509TrustManager() {

		            @Override
					public java.security.cert.X509Certificate[] getAcceptedIssuers() {
		                return null;
		            }
		            @Override
		            public void checkClientTrusted(
		                    java.security.cert.X509Certificate[] certs, String authType) {
		            }
		            @Override
		            public void checkServerTrusted(
		                    java.security.cert.X509Certificate[] certs, String authType) {
		            }
		        }};
		
		SSLContext ctx = SSLContext.getInstance("SSL");
		ctx.init(null, trustAllCerts, new SecureRandom());
		HTTPSProperties prop = new HTTPSProperties(
		new HostnameVerifier () {
			@Override
		    public boolean verify(String hostname, SSLSession session) {
		        System.out.println("\n\nFAKE_Verifier: " + hostname+"\n\n");
		        return true;
		    }
		}, ctx);
		clientConfig.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, prop);
		
		final Client client = Client.create(clientConfig);
		client.addFilter(new ClientFilter() {
			@Override
			public ClientResponse handle(final ClientRequest cr) throws ClientHandlerException {
				return this.getNext().handle(cr);
			}
		});
		
		
		// Init web resource
//		webResource = client.resource("http://localhost:8080/aws-test/api/v1.0");
		webResource = client.resource("https://localhost:8443/aws-test/api/v1.0");
//		webResource = client.resource("https://46.137.142.11:443/aws-test/api/v1.0");
//		webResource = client.resource("http://aws-test.byob.fr/aws-test/api/v1.0");
//		webResource = client.resource("http://46.137.142.11/aws-test/api/v1.0");

		//		webResource = client.resource("http://localhost:10080/api/v1.0");

	}
}
