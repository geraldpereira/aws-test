package fr.byob.aws.dynamodb.dao.impl;

import static fr.byob.aws.db.dao.ProductDAO.AUTHORS;
import static fr.byob.aws.db.dao.ProductDAO.CATEGORY;
import static fr.byob.aws.db.dao.ProductDAO.DIMENSIONS;
import static fr.byob.aws.db.dao.ProductDAO.ID;
import static fr.byob.aws.db.dao.ProductDAO.IN_PUBLICATION;
import static fr.byob.aws.db.dao.ProductDAO.ISBN;
import static fr.byob.aws.db.dao.ProductDAO.PRICE;
import static fr.byob.aws.db.dao.ProductDAO.TITLE;
import static fr.byob.aws.dynamodb.dao.impl.AttributeValueConverter.booleanToAttributeValue;
import static fr.byob.aws.dynamodb.dao.impl.AttributeValueConverter.doubleToAttributeValue;
import static fr.byob.aws.dynamodb.dao.impl.AttributeValueConverter.integerToAttributeValue;
import static fr.byob.aws.dynamodb.dao.impl.AttributeValueConverter.stringToAttributeValue;
import static fr.byob.aws.dynamodb.dao.impl.AttributeValueConverter.stringsToAttributeValue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.dynamodb.model.AttributeValue;

import fr.byob.aws.domain.Product;

final class ProductTestUtils {

	public final static Product newProduct() {
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

	public final static Map<String, AttributeValue> newItem() {

		Map<String, AttributeValue> item = new HashMap<>();
		item.put(ID, integerToAttributeValue(130));
		item.put(TITLE, stringToAttributeValue("test1"));
		item.put(ISBN, stringToAttributeValue("test2"));
		item.put(AUTHORS,
				stringsToAttributeValue(Arrays.asList("test3", "test4")));
		item.put(PRICE, doubleToAttributeValue(20.99));
		item.put(CATEGORY, stringToAttributeValue("test5"));
		item.put(DIMENSIONS, stringToAttributeValue("test6"));
		item.put(IN_PUBLICATION, booleanToAttributeValue(true));
		return item;
	}

}
