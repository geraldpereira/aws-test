package fr.byob.aws.dynamodb.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodb.model.AttributeValue;

final class AttributeValueConverter {
	
	public final static AttributeValue stringToAttributeValue(final String value){
		return value != null ? new AttributeValue().withS(value) : null;
	}
	
	public final static AttributeValue integerToAttributeValue(final Integer value){
		return value != null ? new AttributeValue().withN(value.toString()) : null;
	}
	
	public final static AttributeValue doubleToAttributeValue(final Double value){
		return value != null ? new AttributeValue().withN(value.toString()) : null;
	}
	
	public final static AttributeValue stringsToAttributeValue(final List<String> values){
		return values != null ? new AttributeValue().withSS(values) : null;
	}
	
	public final static AttributeValue booleanToAttributeValue(final Boolean value){
		return value != null ? new AttributeValue().withN(value ? "1" : "0") : null;
	}
	
	public final static String attributeValueToString(final AttributeValue value){
		return value != null ? value.getS() : null;
	}
	
	public final static Integer attributeValueToInteger(final AttributeValue value){
		return value != null ? Integer.valueOf(value.getN()) : null;
	}
	
	public final static Double attributeValueToDouble(final AttributeValue value){
		return value != null ? Double.valueOf(value.getN()) : null;
	}
	
	public final static List<String> attributeValueToStrings(final AttributeValue value){
		return value != null ?  new ArrayList<>(value.getSS()) : null;
	}
	
	public final static Boolean attributeValueToBoolean(final AttributeValue value){
		return value != null ? "1".equals(value.getN()) : null;
	}
}
