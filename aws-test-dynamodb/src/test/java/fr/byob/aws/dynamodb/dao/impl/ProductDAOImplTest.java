package fr.byob.aws.dynamodb.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import fr.byob.aws.db.DAOException;
import fr.byob.aws.db.dao.ProductDAO;
import fr.byob.aws.domain.Product;
import fr.byob.aws.domain.ProductBuilder;

public class ProductDAOImplTest {

	protected static Injector injector;

	@BeforeClass
	public static void beforeClass() {
		injector = Guice.createInjector(new DynamoDBModule());
	}
	
	private ProductDAO productDAO;
	private Product product;

	@Before
	public void before() {
		productDAO = injector.getInstance(ProductDAO.class);
		assertNotNull(productDAO);
		product = new ProductBuilder().build();
		assertNotNull(product);
	}

	@Test
	public void simpleTest() throws DAOException {
		productDAO.createProduct(product);
		assertEquals(product, productDAO.retrieveProduct(product.getId()));
		productDAO.deleteProduct(product.getId());
	}
	
	@Test(expected=DAOException.class)
	public void notFoundTest() throws DAOException {
		productDAO.retrieveProduct(100);
	}
	
	@Test(expected=DAOException.class)
	public void nullIdRetrieveTest() throws DAOException {
		productDAO.retrieveProduct(null);
	}
	
	@Test(expected=DAOException.class)
	public void nullIdDeleteTest() throws DAOException {
		productDAO.deleteProduct(null);
	}
	
	@Test
	public void nullAttributeTest() throws DAOException {
		product.setIsbn(null);
		productDAO.createProduct(product);
		assertEquals(product, productDAO.retrieveProduct(product.getId()));
		productDAO.deleteProduct(product.getId());
	}
	
	@Test(expected=DAOException.class)
	public void nullIdTest() throws DAOException {
		product.setId(null);
		productDAO.createProduct(product);
	}

}
