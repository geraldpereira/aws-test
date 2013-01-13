package com.byob.aws.ant;

import org.apache.tools.ant.BuildException;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;

/**
 * Abstract task for EC2 
 * 
 * @author Kojiro
 *
 */
public abstract class EC2Task extends AWSTask{
	
	protected AmazonEC2 client;
	
	public EC2Task(){
		super();
		
	}

	@Override
	public void execute() throws BuildException {
		super.execute();
		client = new AmazonEC2Client(credentials);
		client.setEndpoint("https://"+region+".ec2.amazonaws.com");		
	}
	
}
