package fr.byob.game.memeduel.server.rest;

import static org.junit.Assert.*;

import javax.ws.rs.core.MediaType;

import org.junit.Test;

import fr.byob.aws.domain.Product;
import fr.byob.aws.domain.ProductBuilder;


/**
 * Uses Jersey client to test the aws-test war
 * 
 * @author gpereira
 *
 */
public class ProductResourceTest extends AbstractResourceTest {

	@Test
	public void testLevel() {

		final Product product =  new ProductBuilder().build();
		String id = webResource.path("/product/add/").type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(String.class,product);
		assertNotNull(id);
		product.setId(id);
		final Product returned = webResource.path("/product/get/"+id).type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get(Product.class);
		assertEquals(product,returned);
		
		webResource.path("/product/delete/"+id).type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).delete();		

	}

}
