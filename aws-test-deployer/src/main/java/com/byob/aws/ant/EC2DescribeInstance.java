package com.byob.aws.ant;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Arrays;

import org.apache.tools.ant.BuildException;

import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;

/**
 * Describes an EC2 instance : its ip address is stored in aws.instance.ip
 * aws.instance.started is true is the instance is in state RUNNING
 * @author gpereira
 *
 */
public class EC2DescribeInstance extends EC2Task {

	private String instanceId;

	@Override
	public void execute() throws BuildException {
		super.execute();
		checkNotNull(instanceId);

		final DescribeInstancesRequest request = new DescribeInstancesRequest();
		request.setInstanceIds(Arrays.asList(instanceId));
		final DescribeInstancesResult result = client.describeInstances(request);
		final Instance instance = result.getReservations().get(0).getInstances().get(0);
		final String ipAddress = instance.getPublicIpAddress();
		final boolean started = "running".equals(instance.getState().getName());
		getProject().setProperty("aws.instance.ip", ipAddress);
		getProject().setProperty("aws.instance.started", ""+started);
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getInstanceId() {
		return instanceId;
	}

}