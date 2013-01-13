aws-test-deployer
=================

This module is used to deploy the local aws-test-rest-war file to AWS : EC2 instances accessed via a load balancer called AWSTestLoadBalancer (deployment processes bellow).
- gets the lists of AWS instances that are load balanced,
- for each instance deregisters it from the load balancer
- deploys the war
- registers the instance with the load balancer
 
This module contains various Ant task classes. These classes are used to access the AWS services : EC2 and Elastic Load Balancer.

- pom.xml : launch with "antrun:run -Pdeploy -e", its a pom wrapper for an ant build that do the deployment. Maven is still usefull for dependency managment.
- deploy.properties : configuration information for the deployment.
- jetty.xml : configuration of the remote jetty server. The $JETTY_HOME/etc/jetty.xml file is overwritten with this one.
- aws-test.xml : jetty context file for our aws-test.war
 
 
EC2 instance deployment process
-------------------------------

The EC2 isntances are basic linux with a jetty server installed.

1. Create a new Amazon Linux instance
2. Upgrade the OS 
    + sudo yum upgrade
3. Install JDK 1.7 // Optionnal for tomcat ? for jetty ?
    + sudo yum install java-1.7.0-openjdk-devel
    + sudo yum remove java-1.6.0-openjdk

4. For jetty :
  - Download and extract http://wiki.eclipse.org/Jetty/Starting/Downloads
  - Install as a service http://jawher.net/2009/12/18/manually-installing-a-recent-version-of-jetty-as-a-service-in-linux/
      + chkconfig --add jetty
      + chkconfig jetty on
  - Configure server for high load http://wiki.eclipse.org/Jetty/Howto/High_Load
  - Configure Iptables http://www.eclipse.org/jetty/documentation/current/setting-port80-access.html
      + sudo iptables -t nat -I PREROUTING -p tcp --dport 80 -j REDIRECT --to-port 8080
      + sudo iptables -t nat -I PREROUTING -p tcp --dport 443 -j REDIRECT --to-port 8443
      + sudo service iptables save
      + sudo service iptables restart
  - Upload war to $JETTY_HOME/webapps
  - Restart jetty 'service jetty restart'

5. sudoers https://forums.aws.amazon.com/thread.jspa?messageID=295990
  - Comment the line 'Defaults requiretty' in /etc/sudoers


LoadBalancer creation process
-----------------------------

1. Create a load balancer called AWSTestLoadBalancer
2. Redirect port 80
3. set ping path to /aws-test/index.jsp
