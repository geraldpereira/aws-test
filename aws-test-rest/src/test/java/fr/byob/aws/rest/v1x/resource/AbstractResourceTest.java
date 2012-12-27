package fr.byob.aws.rest.v1x.resource;

import java.util.Arrays;

import org.junit.BeforeClass;

import com.google.inject.Guice;
import com.google.inject.Injector;

import fr.byob.aws.commons.guice.LoggerModule;
import fr.byob.aws.domain.Product;
import fr.byob.aws.dynamodb.dao.impl.DynamoDBModule;
import fr.byob.aws.rest.RESTJerseyServletModule;

public abstract class AbstractResourceTest {

	protected static Injector injector;
	
	protected final static Product newProduct() {
		Product product = new Product();
		product.setId(130);
		product.setTitle("test1");
		product.setISBN("test2");
		product.setAuthors(Arrays.asList("test3", "test4"));
		product.setPrice(20.99);
		product.setCategory("test5");
		product.setDimensions("test6");
		product.setInPublication(true);
		return product;
	}

	@BeforeClass
	public static void beforeClass() {
		injector = Guice.createInjector(/*new LoggerModule(),*/
				new RESTJerseyServletModule(), new DynamoDBModule());
	}
}
