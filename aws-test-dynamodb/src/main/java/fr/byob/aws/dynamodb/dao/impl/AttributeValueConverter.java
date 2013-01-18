package fr.byob.aws.dynamodb.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodb.model.AttributeValue;

final class AttributeValueConverter {
	
	private AttributeValueConverter(){
		throw new IllegalAccessError();
	}
	
	/**
	 * stringToAttributeValue
	 * @param value
	 * @return
	 */
	public static AttributeValue stringToAttributeValue(final String value){
		return value != null ? new AttributeValue().withS(value) : null;
	}
	/**
	 * integerToAttributeValue
	 * @param value
	 * @return
	 */
	public static AttributeValue integerToAttributeValue(final Integer value){
		return value != null ? new AttributeValue().withN(value.toString()) : null;
	}
	
	/**
	 * doubleToAttributeValue
	 * @param value
	 * @return
	 */
	public static AttributeValue doubleToAttributeValue(final Double value){
		return value != null ? new AttributeValue().withN(value.toString()) : null;
	}
	
	/**
	 * stringsToAttributeValue
	 * @param values
	 * @return
	 */
	public static AttributeValue stringsToAttributeValue(final List<String> values){
		return values != null ? new AttributeValue().withSS(values) : null;
	}
	
	/**
	 * booleanToAttributeValue
	 * @param value
	 * @return
	 */
	public static AttributeValue booleanToAttributeValue(final Boolean value){
		return value != null ? new AttributeValue().withN(value ? "1" : "0") : null;
	}
	
	/**
	 * attributeValueToString
	 * @param value
	 * @return
	 */
	public static String attributeValueToString(final AttributeValue value){
		return value != null ? value.getS() : null;
	}
	
	/**
	 * attributeValueToInteger
	 * @param value
	 * @return
	 */
	public static Integer attributeValueToInteger(final AttributeValue value){
		return value != null ? Integer.valueOf(value.getN()) : null;
	}
	
	/**
	 * attributeValueToDouble
	 * @param value
	 * @return
	 */
	public static Double attributeValueToDouble(final AttributeValue value){
		return value != null ? Double.valueOf(value.getN()) : null;
	}
	
	/**
	 * attributeValueToStrings
	 * @param value
	 * @return
	 */
	public static List<String> attributeValueToStrings(final AttributeValue value){
		return value != null ?  new ArrayList<>(value.getSS()) : null;
	}
	
	/**
	 * attributeValueToBoolean
	 * @param value
	 * @return
	 */
	public static Boolean attributeValueToBoolean(final AttributeValue value){
		return value != null ? "1".equals(value.getN()) : false;
	}
}
