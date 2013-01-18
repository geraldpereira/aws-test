package fr.byob.aws.dynamodb.dao.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.dynamodb.AmazonDynamoDB;
import com.amazonaws.services.dynamodb.AmazonDynamoDBClient;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.name.Names;

import fr.byob.aws.db.dao.ProductDAO;

/**
 * AmazonDynamoDB guice module 
 * 
 * The system property properties.credentials must be set to the path to an AWS crediential.properties file
 * 
 * accessKey=your_aws_key
 * secretKey=your_aws_secret_key
 * 
 * @author gpereira
 *
 */
public final class DynamoDBModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(AmazonDynamoDB.class).toProvider(
				new Provider<AmazonDynamoDB>() {
					@Override
					public AmazonDynamoDB get() {
						AWSCredentials credentials;
						try {
							final String credentialsPath = System.getProperty("properties.credentials");
							checkNotNull(credentialsPath);
							final Path path = Paths.get(credentialsPath).toAbsolutePath();
							checkNotNull(path,"The path is null : "+ credentialsPath);
							credentials = new PropertiesCredentials(path.toFile());
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
