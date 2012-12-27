package fr.byob.aws.dynamodb.dao.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodb.AmazonDynamoDBClient;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.name.Names;

import fr.byob.aws.db.dao.ProductDAO;

public final class DynamoDBModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(AmazonDynamoDBClient.class).toProvider(
				new Provider<AmazonDynamoDBClient>() {
					@Override
					public AmazonDynamoDBClient get() {
						AWSCredentials credentials = new BasicAWSCredentials(
								"AKIAJZCQEUO77Y7D4K2A",
								"vp+SWwm5ppJaC5E01cUEkmt8bJYd00qldL9rpKdf");

						AmazonDynamoDBClient client = new AmazonDynamoDBClient(
								credentials);
						client.setEndpoint("dynamodb.eu-west-1.amazonaws.com");
						return client;
					}
				});
		bind(String.class).annotatedWith(Names.named("product")).toInstance("ProductCatalog");
		bind(ProductDAO.class).to(ProductDAOImpl.class);
	}

}
