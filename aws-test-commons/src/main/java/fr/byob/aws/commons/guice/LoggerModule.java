package fr.byob.aws.commons.guice;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class LoggerModule extends AbstractModule {

	@Override
	protected void configure() {
		bindListener(Matchers.any(), new SLF4JTypeListener());
	}
}