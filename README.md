aws-test
========

This sample application is an AWS test. The NoSQL DynamoDB services are accessed through HTTP(S) Restfull WebServices.

Technos :
- DynamoDB
- Guava
- Guice
- SLF4J / Log4J
- Jetty
- Jersey
- Jackson
- JSON
- JUnit

EC2 instance deployment process
-------------------------------

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

TODO
----

- HTTPS and AWS load balancer => write documentation / blog post for the whole process
- VPC
- JMeter => Long running tests and burst
- system properties => <jettyEnvXml> Optional. Location of a jetty-env.xml file, which allows you to make JNDI bindings that satisfies <env-entry>, <resource-env-ref> and <resource-ref> linkages in the web.xml. Note that these can also be made in a <jettyXml> file if you want them to apply to more than one webapp.
    - http://wiki.eclipse.org/Jetty/Feature/Jetty_Maven_Plugin
- Authentication ? already done for memeduel, it should be straightforward

