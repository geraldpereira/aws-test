package fr.byob.aws.rest.v1x.resource;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import fr.byob.aws.commons.guice.LoggerModule;
import fr.byob.aws.db.DAOException;
import fr.byob.aws.db.dao.ProductDAO;
import fr.byob.aws.domain.Product;
import fr.byob.aws.domain.ProductBuilder;
import fr.byob.aws.rest.ResourcesModule;
import fr.byob.aws.rest.exception.IllegalRequestException;

public class ProductResourceFailTest {

	protected static Injector injector;
	
	@BeforeClass
	public static void beforeClass() {
		injector = Guice.createInjector(new LoggerModule(),
				new ResourcesModule(), new AbstractModule() {
					
					@Override
					protected void configure() {
						bind(ProductDAO.class).to(ProductDAOFail.class);						
					}
				});
	}
	
	private static final class ProductDAOFail implements ProductDAO{

		@Override
		public void createProduct(Product product) throws DAOException {
			throw new DAOException("Error");
		}

		@Override
		public Product retrieveProduct(Integer id) throws DAOException {
			throw new DAOException("Error");
		}

		@Override
		public void deleteProduct(Integer id) throws DAOException {
			throw new DAOException(new NullPointerException("Error"));
		}
		
	}
	

	private ProductResource productResource;
	private Product product;
	
	@Before
	public void before() {
		productResource = injector.getInstance(ProductResource.class);
		assertNotNull(productResource);
		product = new ProductBuilder().build();
		assertNotNull(product);
	}

	
	@Test(expected=IllegalRequestException.class)
	public void testAdd(){
		productResource.add(new ProductBuilder().build());
	}

	@Test(expected=IllegalRequestException.class)
	public void testGet(){
		productResource.get(1);
	}
	
	@Test(expected=IllegalRequestException.class)
	public void testDelete(){
		productResource.delete(1);
	}
}
