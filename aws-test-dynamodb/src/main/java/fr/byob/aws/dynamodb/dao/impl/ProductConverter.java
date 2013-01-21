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
import com.google.common.base.Optional;

import fr.byob.aws.domain.Product;

final class ProductConverter {

	private ProductConverter(){
		throw new IllegalAccessError();
	}
	
	/**
	 * item To Product converter
	 * @param item
	 * @return
	 */
	public static Product itemToProduct(Map<String, AttributeValue> item) {
		checkNotNull(item, "Item cannot be null");
		Product product = new Product(); 
		Optional<Integer> id = attributeValueToInteger(item.get(ID));
		if (id.isPresent()){
			product.setId(id.get());	
		}		
		Optional<String> title = attributeValueToString(item.get(TITLE));
		if (title.isPresent()){
			product.setTitle(title.get());	
		}
		Optional<String> isbn = attributeValueToString(item.get(ISBN));
		if (isbn.isPresent()){
			product.setIsbn(isbn.get());	
		}
		product.setAuthors(attributeValueToStrings(item.get(AUTHORS)));
		Optional<Double> price = attributeValueToDouble(item.get(PRICE));
		if (price.isPresent()){
			product.setPrice(price.get());	
		}
		Optional<String> cat = attributeValueToString(item.get(CATEGORY));
		if (cat.isPresent()){
			product.setCategory(cat.get());	
		}
		Optional<String> dim = attributeValueToString(item.get(DIMENSIONS));
		if (dim.isPresent()){
			product.setDimensions(dim.get());	
		}
		product.setInPublication(attributeValueToBoolean(item.get(IN_PUBLICATION)));
		return product;
	}

	/**
	 * product To Item converter
	 * @param product
	 * @return
	 */
	public static Map<String, AttributeValue> productToItem(Product product) {
		checkNotNull(product, "Product cannot be null");
		Map<String, AttributeValue> item = new HashMap<>();
		Optional<AttributeValue> id = integerToAttributeValue(product.getId());  
		if (id.isPresent()){
			item.put(ID, id.get());	
		}
		Optional<AttributeValue> title = stringToAttributeValue(product.getTitle());
		if (title.isPresent()){
			item.put(TITLE, title.get());	
		}
        
        Optional<AttributeValue> isbn = stringToAttributeValue(product.getIsbn());
        if (isbn.isPresent()){
        	item.put(ISBN, isbn.get());	
        }
        
        Optional<AttributeValue> auths = stringsToAttributeValue(product.getAuthors());
        if (auths.isPresent()){
        	item.put(AUTHORS,auths.get());
        }
        
        Optional<AttributeValue> price = doubleToAttributeValue(product.getPrice());
        if (price.isPresent()){
        	item.put(PRICE, price.get());
        }
        
        Optional<AttributeValue> cat = stringToAttributeValue(product.getCategory());
        if(cat.isPresent()){
        	item.put(CATEGORY,cat.get() );	
        }
        
        Optional<AttributeValue> dims = stringToAttributeValue(product.getDimensions());
        if (dims.isPresent()){
        	item.put(DIMENSIONS, dims.get());	
        }
        
        Optional<AttributeValue> inPublication = booleanToAttributeValue(product.getInPublication());
        if (inPublication.isPresent()){
        	item.put(IN_PUBLICATION, inPublication.get());	
        }
		return item;
	}

}
