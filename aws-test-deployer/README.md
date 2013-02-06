aws-test-deployer
=================

This module is used to deploy the local aws-test-rest-war file to AWS : EC2 instances accessed via a load balancer called AWSTestLoadBalancer (deployment processes bellow).
- gets the lists of AWS instances that are load balanced,
- for each instance deregisters it from the load balancer
- deploys the war
- registers the instance with the load balancer
 
This module contains various Ant task classes. These classes are used to access the AWS services : EC2 and Elastic Load Balancer.

- pom.xml : launch with "antrun:run -Pdeploy -e", its a pom wrapper for an ant build that do the deployment. Maven is still usefull for dependency managment.
- deploy.properties : configuration information for the deployment => used in the ant build.
- jetty.xml : configuration of the remote jetty server. The $JETTY_HOME/etc/jetty.xml file is overwritten with this one.
- aws-test.xml : jetty context file for our aws-test.war
 
