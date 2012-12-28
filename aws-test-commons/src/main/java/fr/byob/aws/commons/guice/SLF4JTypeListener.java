package fr.byob.aws.commons.guice;

import java.lang.reflect.Field;

import org.slf4j.Logger;

import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

class SLF4JTypeListener implements TypeListener {
	
	@Override
	public <T> void hear(TypeLiteral<T> typeLiteral, TypeEncounter<T> typeEncounter) {
		for (Field field : typeLiteral.getRawType().getDeclaredFields()) {
			if (field.getType() == Logger.class && field.isAnnotationPresent(InjectLogger.class)) {
				typeEncounter.register(new SLF4JMembersInjector<T>(field));
			}
		}
	}
}