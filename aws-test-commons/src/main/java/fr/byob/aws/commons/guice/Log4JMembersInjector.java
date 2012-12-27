package fr.byob.aws.commons.guice;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;

import com.google.inject.MembersInjector;

class Log4JMembersInjector<T> implements MembersInjector<T> {
	private final Field field;
	private final Logger logger;

	Log4JMembersInjector(Field field) {
		this.field = field;
		this.logger = Logger.getLogger(field.getDeclaringClass());
		field.setAccessible(true);
	}

	@Override
	public void injectMembers(T t) {
		try {
			field.set(t, logger);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}