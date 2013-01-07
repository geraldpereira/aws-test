package com.byob.aws.ant;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;

/**
 * Must be public for AntRun
 * 
 * @author gpereira
 *
 */
public abstract class AWSTask extends Task{

	private String region;
	
	protected final AWSCredentials credentials;
	
	public AWSTask (){
		super();
		try {
			credentials = new PropertiesCredentials(Paths.get("..","..","conf","credentials.properties").toAbsolutePath().toFile());
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
	
}
