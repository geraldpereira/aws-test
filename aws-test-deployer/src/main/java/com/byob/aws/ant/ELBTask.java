package com.byob.aws.ant;

import com.amazonaws.services.elasticloadbalancing.AmazonElasticLoadBalancing;
import com.amazonaws.services.elasticloadbalancing.AmazonElasticLoadBalancingClient;

public abstract class ELBTask extends AWSTask{
	
	protected String loadBalancerName;
	
	protected final AmazonElasticLoadBalancing client;
	
	public ELBTask(){
		super();
		client = new AmazonElasticLoadBalancingClient(credentials);
	}

	@Override
	public void setRegion(String region) {
		super.setRegion(region);
		client.setEndpoint("https://elasticloadbalancing."+region+".amazonaws.com");
	}
	
	public void setLoadBalancerName(String loadBalancerName) {
		this.loadBalancerName = loadBalancerName;
	}
	
	public String getLoadBalancerName() {
		return loadBalancerName;
	}
}