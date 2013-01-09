package com.byob.aws.ant;

import java.util.Arrays;
import java.util.List;

import org.apache.tools.ant.BuildException;

import com.amazonaws.services.elasticloadbalancing.model.DescribeLoadBalancersRequest;
import com.amazonaws.services.elasticloadbalancing.model.DescribeLoadBalancersResult;
import com.amazonaws.services.elasticloadbalancing.model.Instance;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

/**
 * Lists all aws instances
 * @author gpereira
 *
 */
public class ELBListInstances extends ELBTask{
	
	@Override
	public void execute() throws BuildException {
		super.execute();
		final DescribeLoadBalancersRequest request = new DescribeLoadBalancersRequest(Arrays.asList(LB_NAME));
		final DescribeLoadBalancersResult result = client.describeLoadBalancers(request);
		final List<String> instancesId = Lists.transform(result.getLoadBalancerDescriptions().get(0).getInstances(), new Function<Instance, String>() {
			public String apply(Instance input) {
				return input.getInstanceId();
			}
		});
		
		final String joined = Joiner.on(',').join(instancesId);
		getProject().setProperty("aws.instances", joined);
	}
}
