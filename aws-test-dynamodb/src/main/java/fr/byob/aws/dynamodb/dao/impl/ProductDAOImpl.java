package fr.byob.aws.dynamodb.dao.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodb.datamodeling.DynamoDBMapper;
import com.google.inject.Inject;

import fr.byob.aws.db.DAOException;
import fr.byob.aws.db.dao.ProductDAO;
import fr.byob.aws.domain.Product;

final class ProductDAOImpl implements ProductDAO {

	private final DynamoDBMapper mapper;

	/**
	 * Guice injected constructor
	 * @param client
	 * @param tableName
	 */
	@Inject
	public ProductDAOImpl(final DynamoDBMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public String createProduct(Product product) throws DAOException {
		try {
			mapper.save(product);
			return product.getId();
		}catch (AmazonServiceException e){
			throw new DAOException(e);
		}
	}

	@Override
	public Product retrieveProduct(String id) throws DAOException {
		try {
			return mapper.load(Product.class, id);
		}catch (AmazonServiceException e){
			throw new DAOException(e);
		}
	}

	@Override
	public void deleteProduct(String id) throws DAOException {
		try {
			final Product product = new Product();
			product.setId(id);
			mapper.delete(product);
		}catch (AmazonServiceException e){
			throw new DAOException(e);
		}
	}

}
