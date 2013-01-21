package fr.byob.aws.dynamodb.dao.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.junit.Assert.*;
import static fr.byob.aws.dynamodb.dao.impl.AttributeValueConverter.*;

import org.junit.Test;

import com.amazonaws.services.dynamodb.model.AttributeValue;

public class AttributeValueConverterTest {

	
	@Test(expected = InvocationTargetException.class)
	public void testConstructor() throws ReflectiveOperationException {
		final Constructor<AttributeValueConverter> constructor = AttributeValueConverter.class
				.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}

	
	@Test
	public void stringToAttributeValueTest(){
		assertFalse(stringToAttributeValue(null).isPresent());
		assertEquals("test",stringToAttributeValue("test").get().getS());
	}
	
	@Test
	public void integerToAttributeValueTest(){
		assertFalse(integerToAttributeValue(null).isPresent());
		assertEquals("10",integerToAttributeValue(10).get().getN());
	}
	
	@Test
	public void doubleToAttributeValueTest(){
		assertFalse(doubleToAttributeValue(null).isPresent());
		assertEquals("10.0",doubleToAttributeValue(10d).get().getN());
	}
	
	@Test
	public void stringsToAttributeValueTest(){
		assertFalse(stringsToAttributeValue(null).isPresent());
		assertEquals(Arrays.asList("test","test1"),stringsToAttributeValue(Arrays.asList("test","test1")).get().getSS());
	}
	
	@Test
	public void booleanToAttributeValueTest(){
		assertFalse(booleanToAttributeValue(null).isPresent());
		assertEquals("1",booleanToAttributeValue(true).get().getN());
		assertEquals("0",booleanToAttributeValue(false).get().getN());
	}
	
	@Test
	public void attributeValueToStringTest(){
		assertFalse(attributeValueToString(null).isPresent());
		assertEquals("test",attributeValueToString(new AttributeValue("test")).get());
	}
	
	@Test
	public void attributeValueToIntegerTest(){
		assertFalse(attributeValueToInteger(null).isPresent());
		assertEquals(Integer.valueOf(10),attributeValueToInteger(new AttributeValue().withN("10")).get());
	}
	
	@Test
	public void attributeValueToDoubleTest(){
		assertFalse(attributeValueToDouble(null).isPresent());
		assertEquals(Double.valueOf(10d),attributeValueToDouble(new AttributeValue().withN("10.0")).get());
	}
	
	@Test
	public void attributeValueToStringsTest(){
		assertTrue(attributeValueToStrings(null).isEmpty());
		assertEquals(Arrays.asList("test","test1"),attributeValueToStrings(new AttributeValue(Arrays.asList("test","test1"))));
	}
	
	@Test
	public void attributeValueToBooleanTest(){
		assertFalse(attributeValueToBoolean(null));
		assertFalse(attributeValueToBoolean(new AttributeValue().withN("0")));
		assertTrue(attributeValueToBoolean(new AttributeValue().withN("1")));
	}
}
