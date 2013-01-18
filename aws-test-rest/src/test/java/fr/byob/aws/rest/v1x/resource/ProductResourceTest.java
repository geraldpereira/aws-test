package fr.byob.aws.rest.v1x.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;

import fr.byob.aws.domain.Product;
import fr.byob.aws.domain.ProductBuilder;

public class ProductResourceTest extends AbstractResourceTest {


	private ProductResource productResource;
	private Product product;
	
	@Before
	public void before() {
		productResource = injector.getInstance(ProductResource.class);
		assertNotNull(productResource);
		product = new ProductBuilder().build();
		assertNotNull(product);
	}

	
	@Test
	public void simpleTest() throws UnknownHostException{
		productResource.add(product);
		assertEquals(product, productResource.get(product.getId()));
		productResource.delete(product.getId());
		assertEquals(InetAddress.getLocalHost().getHostName(), productResource.test());
	}

}
