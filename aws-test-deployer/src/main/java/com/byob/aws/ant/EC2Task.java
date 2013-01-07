package com.byob.aws.ant;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;

public abstract class EC2Task extends AWSTask{
	
	protected final AmazonEC2 client;
	
	public EC2Task(){
		super();
		client = new AmazonEC2Client(credentials);
	}

	@Override
	public void setRegion(String region) {
		super.setRegion(region);
		client.setEndpoint("https://"+region+".ec2.amazonaws.com");
	}
}
