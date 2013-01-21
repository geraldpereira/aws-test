package fr.byob.aws.dynamodb.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.amazonaws.services.dynamodb.model.AttributeValue;
import com.google.common.base.Optional;

final class AttributeValueConverter {

	private AttributeValueConverter() {
		throw new IllegalAccessError();
	}

	/**
	 * stringToAttributeValue
	 * 
	 * @param value
	 * @return
	 */
	public static Optional<AttributeValue> stringToAttributeValue(
			final String value) {
		return Optional.fromNullable(value != null ? new AttributeValue()
				.withS(value) : null);
	}

	/**
	 * integerToAttributeValue
	 * 
	 * @param value
	 * @return
	 */
	public static Optional<AttributeValue> integerToAttributeValue(
			final Integer value) {
		return Optional.fromNullable(value != null ? new AttributeValue()
				.withN(value.toString()) : null);
	}

	/**
	 * doubleToAttributeValue
	 * 
	 * @param value
	 * @return
	 */
	public static Optional<AttributeValue> doubleToAttributeValue(
			final Double value) {
		return Optional.fromNullable(value != null ? new AttributeValue()
				.withN(value.toString()) : null);
	}

	/**
	 * stringsToAttributeValue
	 * 
	 * @param values
	 * @return
	 */
	public static Optional<AttributeValue> stringsToAttributeValue(
			final List<String> values) {
		return Optional.fromNullable(values != null ? new AttributeValue()
				.withSS(values) : null);
	}

	/**
	 * booleanToAttributeValue
	 * 
	 * @param value
	 * @return
	 */
	public static Optional<AttributeValue> booleanToAttributeValue(
			final Boolean value) {
		return Optional.fromNullable(value != null ? new AttributeValue()
				.withN(value ? "1" : "0") : null);
	}

	/**
	 * attributeValueToString
	 * 
	 * @param value
	 * @return
	 */
	public static Optional<String> attributeValueToString(
			final AttributeValue value) {
		return Optional.fromNullable(value != null ? value.getS() : null);
	}

	/**
	 * attributeValueToInteger
	 * 
	 * @param value
	 * @return
	 */
	public static Optional<Integer> attributeValueToInteger(
			final AttributeValue value) {
		return Optional.fromNullable(value != null ? Integer.valueOf(value
				.getN()) : null);
	}

	/**
	 * attributeValueToDouble
	 * 
	 * @param value
	 * @return
	 */
	public static Optional<Double> attributeValueToDouble(
			final AttributeValue value) {
		return Optional.fromNullable(value != null ? Double.valueOf(value
				.getN()) : null);
	}

	/**
	 * attributeValueToStrings
	 * 
	 * @param value
	 * @return
	 */
	public static List<String> attributeValueToStrings(
			final AttributeValue value) {
		return value != null ? new ArrayList<>(value.getSS())
				: Collections.EMPTY_LIST;
	}

	/**
	 * attributeValueToBoolean
	 * 
	 * @param value
	 * @return
	 */
	public static boolean attributeValueToBoolean(final AttributeValue value) {
		return value != null ? "1".equals(value.getN()) : false;
	}
}
