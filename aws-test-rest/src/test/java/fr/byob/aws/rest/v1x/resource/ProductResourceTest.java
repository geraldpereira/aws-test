package fr.byob.aws.rest.v1x.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import fr.byob.aws.domain.Product;

public class ProductResourceTest extends AbstractResourceTest {


	private ProductResource productResource;
	private Product product;
	
	@Before
	public void before() {
		productResource = injector.getInstance(ProductResource.class);
		assertNotNull(productResource);
		product = newProduct();
		assertNotNull(product);
	}

	
	@Test
	public void simpleTest(){
		productResource.add(product);
		assertEquals(product, productResource.get(product.getId()));
		productResource.delete(product.getId());
	}

}
