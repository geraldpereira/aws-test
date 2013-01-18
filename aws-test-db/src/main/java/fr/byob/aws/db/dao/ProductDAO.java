package fr.byob.aws.db.dao;

import fr.byob.aws.db.DAOException;
import fr.byob.aws.domain.Product;

/**
 * CRUD for the Product bean 
 * 
 * @author gpereira
 *
 */
public interface ProductDAO {

	public static final  String ID = "Id";
	public static final  String TITLE = "Title";
	public static final  String ISBN = "ISBN";
	public static final  String AUTHORS = "Authors";
	public static final  String PRICE = "Price";
	public static final  String CATEGORY = "Category";
	public static final  String DIMENSIONS = "Dimensions";
	public static final  String IN_PUBLICATION = "InPublication";

	/**
	 * Add a new product in the database
	 * @param product the created product to add
	 * @throws DAOException if the add operation failed
	 */
	void createProduct(Product product) throws DAOException;

	/**
	 * Return a product from the db
	 * @param id the id of the product to return 
	 * @return the product with id 'id'
	 * @throws DAOException if the retrieve operation failed
	 */
	Product retrieveProduct(Integer id) throws DAOException;

	/**
	 * Removes a product from DB 
	 * @param id the product id
	 * @throws DAOException if the delete operation failed
	 */
	void deleteProduct(Integer id) throws DAOException;

}
