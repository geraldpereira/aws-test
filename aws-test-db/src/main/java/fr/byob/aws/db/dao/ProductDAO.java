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

	/**
	 * Add a new product in the database
	 * @param product the created product to add
	 * @return the created product id 
	 * @throws DAOException if the add operation failed
	 */
	String createProduct(Product product) throws DAOException;

	/**
	 * Return a product from the db
	 * @param id the id of the product to return 
	 * @return the product with id 'id'
	 * @throws DAOException if the retrieve operation failed
	 */
	Product retrieveProduct(String id) throws DAOException;

	/**
	 * Removes a product from DB 
	 * @param id the product id
	 * @throws DAOException if the delete operation failed
	 */
	void deleteProduct(String id) throws DAOException;

}
