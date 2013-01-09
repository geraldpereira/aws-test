package com.byob.aws.ant;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Arrays;

import org.apache.tools.ant.BuildException;

import com.amazonaws.services.elasticloadbalancing.model.DeregisterInstancesFromLoadBalancerRequest;
import com.amazonaws.services.elasticloadbalancing.model.Instance;

/**
 * Remove Instance from Load Balancer
 * 
 * @author gpereira
 *
 */
public class ELBDeregisterInstance extends ELBTask {

	private String instanceId;

	@Override
	public void execute() throws BuildException {
		super.execute();
		checkNotNull(instanceId);

		final DeregisterInstancesFromLoadBalancerRequest request = new DeregisterInstancesFromLoadBalancerRequest(LB_NAME,Arrays.asList(new Instance(instanceId)));
		client.deregisterInstancesFromLoadBalancer(request);
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getInstanceId() {
		return instanceId;
	}

}
