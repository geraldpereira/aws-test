package fr.byob.aws.dynamodb.dao.impl;

import static fr.byob.aws.dynamodb.dao.impl.AttributeValueConverter.integerToAttributeValue;
import static fr.byob.aws.dynamodb.dao.impl.ProductConverter.itemToProduct;
import static fr.byob.aws.dynamodb.dao.impl.ProductConverter.productToItem;

import java.util.Map;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodb.AmazonDynamoDB;
import com.amazonaws.services.dynamodb.model.AttributeValue;
import com.amazonaws.services.dynamodb.model.DeleteItemRequest;
import com.amazonaws.services.dynamodb.model.GetItemRequest;
import com.amazonaws.services.dynamodb.model.GetItemResult;
import com.amazonaws.services.dynamodb.model.Key;
import com.amazonaws.services.dynamodb.model.PutItemRequest;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import fr.byob.aws.db.DAOException;
import fr.byob.aws.db.dao.ProductDAO;
import fr.byob.aws.domain.Product;

final class ProductDAOImpl implements ProductDAO {

	private final AmazonDynamoDB client;
	private final String tableName;

	/**
	 * Guice injected constructor
	 * @param client
	 * @param tableName
	 */
	@Inject
	public ProductDAOImpl(final AmazonDynamoDB client,
			@Named("product") final String tableName) {
		this.client = client;
		this.tableName = tableName;
	}

	@Override
	public void createProduct(Product product) throws DAOException {
		try {
			final Map<String, AttributeValue> item = productToItem(product);
			final PutItemRequest request = new PutItemRequest().withTableName(
					tableName).withItem(item);
			client.putItem(request);
		} catch (AmazonServiceException ase) {
			throw new DAOException(ase);
		}
	}

	@Override
	public Product retrieveProduct(Integer id) throws DAOException {
		try {

			final GetItemRequest getItemRequest = new GetItemRequest().withTableName(
					tableName).withKey(
					new Key().withHashKeyElement(integerToAttributeValue(id)));

			final GetItemResult result = client.getItem(getItemRequest);

			if (result.getItem() == null){
				throw new DAOException("No result found");				
			}
			
			return itemToProduct(result.getItem());
		} catch (AmazonServiceException ase) {
			throw new DAOException(ase);
		}
	}

	@Override
	public void deleteProduct(Integer id) throws DAOException {
		try {

			final Key key = new Key().withHashKeyElement(integerToAttributeValue(id));

			final DeleteItemRequest deleteItemRequest = new DeleteItemRequest()
					.withTableName(tableName).withKey(key);

			client.deleteItem(deleteItemRequest);

		} catch (AmazonServiceException ase) {
			throw new DAOException(ase);
		}

	}

}
