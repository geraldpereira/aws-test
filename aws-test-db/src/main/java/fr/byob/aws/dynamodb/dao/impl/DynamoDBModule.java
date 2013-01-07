package fr.byob.aws.dynamodb.dao.impl;

import java.io.IOException;
import java.nio.file.Paths;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.dynamodb.AmazonDynamoDB;
import com.amazonaws.services.dynamodb.AmazonDynamoDBClient;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.name.Names;

import fr.byob.aws.db.dao.ProductDAO;

public final class DynamoDBModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(AmazonDynamoDB.class).toProvider(
				new Provider<AmazonDynamoDB>() {
					@Override
					public AmazonDynamoDB get() {
						AWSCredentials credentials;
						try {
							credentials = new PropertiesCredentials(Paths.get("..","..","conf","credentials.properties").toAbsolutePath().toFile());
						} catch (IOException e) {
							return null;
						}

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
