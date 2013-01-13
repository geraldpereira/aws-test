package com.byob.aws.ant;
import static com.google.common.base.Preconditions.checkNotNull;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;

/**
 * A base AWS ant task
 * 
 * region : the aws region
 * credentialsPath : the path to the properties file that contains aws credentials (configured in the deploy.properties file)
 * 
 * Note : Must be public for AntRun
 * 
 * @author gpereira
 *
 */
public abstract class AWSTask extends Task{

	protected String region;
	
	private String credentialsPath;
	
	protected AWSCredentials credentials;
	
	public AWSTask (){
		super();
	}
	
	@Override
	public void execute() throws BuildException {
		super.execute();
		checkNotNull(credentialsPath);
		checkNotNull(region);
		try {
			credentials = new PropertiesCredentials(Paths.get(credentialsPath).toAbsolutePath().toFile());
		} catch (IOException e) {
			throw new BuildException(e);
		}
	}

	public void setRegion(String region) {
		this.region = region;
	}
	
	public String getRegion() {
		return region;
	}
	
	public void setCredentialsPath(String credentialsPath) {
		this.credentialsPath = credentialsPath;
	}
	
	public String getCredentialsPath() {
		return credentialsPath;
	}
	
}
