package fr.byob.aws.dynamodb.dao.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import static fr.byob.aws.db.dao.ProductDAO.AUTHORS;
import static fr.byob.aws.db.dao.ProductDAO.CATEGORY;
import static fr.byob.aws.db.dao.ProductDAO.DIMENSIONS;
import static fr.byob.aws.db.dao.ProductDAO.ID;
import static fr.byob.aws.db.dao.ProductDAO.IN_PUBLICATION;
import static fr.byob.aws.db.dao.ProductDAO.ISBN;
import static fr.byob.aws.db.dao.ProductDAO.PRICE;
import static fr.byob.aws.db.dao.ProductDAO.TITLE;
import static fr.byob.aws.dynamodb.dao.impl.AttributeValueConverter.attributeValueToBoolean;
import static fr.byob.aws.dynamodb.dao.impl.AttributeValueConverter.attributeValueToDouble;
import static fr.byob.aws.dynamodb.dao.impl.AttributeValueConverter.attributeValueToInteger;
import static fr.byob.aws.dynamodb.dao.impl.AttributeValueConverter.attributeValueToString;
import static fr.byob.aws.dynamodb.dao.impl.AttributeValueConverter.attributeValueToStrings;
import static fr.byob.aws.dynamodb.dao.impl.AttributeValueConverter.booleanToAttributeValue;
import static fr.byob.aws.dynamodb.dao.impl.AttributeValueConverter.doubleToAttributeValue;
import static fr.byob.aws.dynamodb.dao.impl.AttributeValueConverter.integerToAttributeValue;
import static fr.byob.aws.dynamodb.dao.impl.AttributeValueConverter.stringToAttributeValue;
import static fr.byob.aws.dynamodb.dao.impl.AttributeValueConverter.stringsToAttributeValue;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.dynamodb.model.AttributeValue;

import fr.byob.aws.domain.Product;

final class ProductConverter {

	private ProductConverter(){
		throw new IllegalAccessError();
	}
	
	public static Product itemToProduct(Map<String, AttributeValue> item) {
		checkNotNull(item, "Item cannot be null");
		Product product = new Product(); 
		product.setId(attributeValueToInteger(item.get(ID)));
		product.setTitle(attributeValueToString(item.get(TITLE)));
		product.setIsbn(attributeValueToString(item.get(ISBN)));
		product.setAuthors(attributeValueToStrings(item.get(AUTHORS)));
		product.setPrice(attributeValueToDouble(item.get(PRICE)));
		product.setCategory(attributeValueToString(item.get(CATEGORY)));
		product.setDimensions(attributeValueToString(item.get(DIMENSIONS)));
		product.setInPublication(attributeValueToBoolean(item.get(IN_PUBLICATION)));
		return product;
	}

	public static Map<String, AttributeValue> productToItem(Product product) {
		checkNotNull(product, "Product cannot be null");
		Map<String, AttributeValue> item = new HashMap<>();
        item.put(ID, integerToAttributeValue(product.getId()));
        item.put(TITLE, stringToAttributeValue(product.getTitle()));
        item.put(ISBN, stringToAttributeValue(product.getIsbn()));
        item.put(AUTHORS,stringsToAttributeValue(product.getAuthors()));
        item.put(PRICE, doubleToAttributeValue(product.getPrice()));
        item.put(CATEGORY, stringToAttributeValue(product.getCategory()));
        item.put(DIMENSIONS, stringToAttributeValue(product.getDimensions()));
        item.put(IN_PUBLICATION, booleanToAttributeValue(product.getInPublication()));
		return item;
	}

}
