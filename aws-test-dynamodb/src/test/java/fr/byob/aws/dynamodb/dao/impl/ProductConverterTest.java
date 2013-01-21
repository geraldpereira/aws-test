package fr.byob.aws.dynamodb.dao.impl;

import static fr.byob.aws.db.dao.ProductDAO.PRICE;
import static fr.byob.aws.dynamodb.dao.impl.AttributeValueConverter.doubleToAttributeValue;
import static fr.byob.aws.dynamodb.dao.impl.ProductConverter.itemToProduct;
import static fr.byob.aws.dynamodb.dao.impl.ProductConverter.productToItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.amazonaws.services.dynamodb.model.AttributeValue;

import fr.byob.aws.domain.Product;
import fr.byob.aws.domain.ProductBuilder;

public class ProductConverterTest {

	
	private Product product;
	private Map<String, AttributeValue> item;
	
	@Before
	public void init (){
		product = new ProductBuilder().build(); 
		item = ProductTestUtils.newItem();
	}
	
	
	@Test
	public void productToMapTest() {
		Map<String, AttributeValue> newItem = productToItem(product);
		assertEquals(item, newItem);
		
		product.setPrice(20d);
		item.put(PRICE, doubleToAttributeValue(20d).get());
		newItem = productToItem(product);
		assertEquals(item, newItem);
		
		product.setPrice(null);
		newItem = productToItem(product);
		assertNull(newItem.get(PRICE));
	}
	
	@Test
	public void mapToProductTest() {
		Product newProduct = itemToProduct(item);
		assertEquals(product, newProduct);

		item.put(PRICE, null);
		newProduct = itemToProduct(item);
		assertNull(newProduct.getPrice());
		
		item.remove(PRICE);
		newProduct = itemToProduct(item);
		assertNull(newProduct.getPrice());
	}
	
	
	

}
