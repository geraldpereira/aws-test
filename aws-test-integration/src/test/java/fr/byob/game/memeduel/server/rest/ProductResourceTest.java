package fr.byob.game.memeduel.server.rest;

import static org.junit.Assert.assertEquals;

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
		Product returned = webResource.path("/product/add/").type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(Product.class,product);
		assertEquals(product,returned);
		
		returned = webResource.path("/product/get/"+product.getId()).type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get(Product.class);
		assertEquals(product,returned);
		
		webResource.path("/product/delete/"+product.getId()).type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).delete();		

	}

}
