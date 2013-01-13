package com.byob.aws.ant;

import org.apache.tools.ant.BuildException;

import com.amazonaws.services.elasticloadbalancing.AmazonElasticLoadBalancing;
import com.amazonaws.services.elasticloadbalancing.AmazonElasticLoadBalancingClient;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Abstract task for Elastic Load Balancer 
 * 
 * @author Kojiro
 *
 */
public abstract class ELBTask extends AWSTask{
	
	protected String loadBalancerName;
	
	protected AmazonElasticLoadBalancing client;
	
	public ELBTask(){
		super();
	}

	@Override
	public void execute() throws BuildException {
		super.execute();
		checkNotNull(loadBalancerName);
		client = new AmazonElasticLoadBalancingClient(credentials);
		client.setEndpoint("https://elasticloadbalancing."+region+".amazonaws.com");
	}
	
	public void setLoadBalancerName(String loadBalancerName) {
		this.loadBalancerName = loadBalancerName;
	}
	
	public String getLoadBalancerName() {
		return loadBalancerName;
	}
}