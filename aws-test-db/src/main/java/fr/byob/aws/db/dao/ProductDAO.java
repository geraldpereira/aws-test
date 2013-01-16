package fr.byob.aws.db.dao;

import fr.byob.aws.db.DAOException;
import fr.byob.aws.domain.Product;

public interface ProductDAO {

	public final static String ID = "Id";
	public final static String TITLE = "Title";
	public final static String ISBN = "ISBN";
	public final static String AUTHORS = "Authors";
	public final static String PRICE = "Price";
	public final static String CATEGORY = "Category";
	public final static String DIMENSIONS = "Dimensions";
	public final static String IN_PUBLICATION = "InPublication";

	public void createProduct(Product product) throws DAOException;

	public Product retrieveProduct(Integer id) throws DAOException;

	public void deleteProduct(Integer id) throws DAOException;

}
