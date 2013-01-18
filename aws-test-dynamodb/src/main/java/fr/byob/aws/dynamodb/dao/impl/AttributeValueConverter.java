package fr.byob.aws.dynamodb.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodb.model.AttributeValue;

final class AttributeValueConverter {
	
	private AttributeValueConverter(){
		throw new IllegalAccessError();
	}
	
	public static final AttributeValue stringToAttributeValue(final String value){
		return value != null ? new AttributeValue().withS(value) : null;
	}
	
	public static final AttributeValue integerToAttributeValue(final Integer value){
		return value != null ? new AttributeValue().withN(value.toString()) : null;
	}
	
	public static final AttributeValue doubleToAttributeValue(final Double value){
		return value != null ? new AttributeValue().withN(value.toString()) : null;
	}
	
	public static final AttributeValue stringsToAttributeValue(final List<String> values){
		return values != null ? new AttributeValue().withSS(values) : null;
	}
	
	public static final AttributeValue booleanToAttributeValue(final Boolean value){
		return value != null ? new AttributeValue().withN(value ? "1" : "0") : null;
	}
	
	public static final String attributeValueToString(final AttributeValue value){
		return value != null ? value.getS() : null;
	}
	
	public static final Integer attributeValueToInteger(final AttributeValue value){
		return value != null ? Integer.valueOf(value.getN()) : null;
	}
	
	public static final Double attributeValueToDouble(final AttributeValue value){
		return value != null ? Double.valueOf(value.getN()) : null;
	}
	
	public static final List<String> attributeValueToStrings(final AttributeValue value){
		return value != null ?  new ArrayList<>(value.getSS()) : null;
	}
	
	public static final Boolean attributeValueToBoolean(final AttributeValue value){
		return value != null ? "1".equals(value.getN()) : null;
	}
}
