package fr.byob.aws.rest;

import com.google.inject.AbstractModule;

import fr.byob.aws.rest.v1x.resource.ProductResource;

/**
 * Guice module to configure REST resources
 * 
 * @author gpereira
 *
 */
public class ResourcesModule extends AbstractModule{

	@Override
	protected void configure() {
		/* bind the REST resources */
		bind(ProductResource.class);
	}

}
