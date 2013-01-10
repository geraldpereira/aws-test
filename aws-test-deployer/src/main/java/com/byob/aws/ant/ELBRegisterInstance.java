package com.byob.aws.ant;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Arrays;

import org.apache.tools.ant.BuildException;

import com.amazonaws.services.elasticloadbalancing.model.Instance;
import com.amazonaws.services.elasticloadbalancing.model.RegisterInstancesWithLoadBalancerRequest;

/**
 * Add Instance to Load Balancer
 * 
 * @author gpereira
 *
 */
public class ELBRegisterInstance extends ELBTask {

	private String instanceId;

	@Override
	public void execute() throws BuildException {
		super.execute();
		checkNotNull(instanceId);

		final RegisterInstancesWithLoadBalancerRequest request = new RegisterInstancesWithLoadBalancerRequest(loadBalancerName,Arrays.asList(new Instance(instanceId)));
		client.registerInstancesWithLoadBalancer(request);
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getInstanceId() {
		return instanceId;
	}

}
