package fr.byob.aws.dynamodb.dao.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import fr.byob.aws.db.DAOException;
import fr.byob.aws.db.dao.ProductDAO;
import fr.byob.aws.domain.Product;

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
		product = ProductTestUtils.newProduct();
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
	
	@Test
	public void nullAttributeTest() throws DAOException {
		product.setISBN(null);
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
