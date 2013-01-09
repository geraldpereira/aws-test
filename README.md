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

EC2 instance deployment process :
- Create a new Amazon Linux instance
- Upgrade the OS 
    + sudo yum upgrade
- Install JDK 1.7 // Optionnal for tomcat ? for jetty ?
    + sudo yum install java-1.7.0-openjdk-devel
    + sudo yum remove java-1.6.0-openjdk

- For jetty :
  - Download and extract http://wiki.eclipse.org/Jetty/Starting/Downloads
  - Install as a service http://jawher.net/2009/12/18/manually-installing-a-recent-version-of-jetty-as-a-service-in-linux/
      + chkconfig --add jetty
      + chkconfig jetty on
  - Configure server for high load http://wiki.eclipse.org/Jetty/Howto/High_Load
  - Configure Iptables http://www.eclipse.org/jetty/documentation/current/setting-port80-access.html
      + sudo iptables -t nat -I PREROUTING -p tcp --dport 80 -j REDIRECT --to-port 8080
      + sudo service iptables save
      + sudo service iptables restart
  - Upload war to $JETTY_HOME/webapps
  - Restart jetty 'service jetty restart'

- sudoers https://forums.aws.amazon.com/thread.jspa?messageID=295990
  - Comment the line 'Defaults requiretty' in /etc/sudoers

Or use the public image : ami-6c2e2118

LoadBalancer creation process
- Create a load balancer called AWSTestLoadBalancer
- Redirect port 80
- set ping path to /aws-test/index.jsp

TODO
DynamoDBModule works only when a conf file is located in ../../conf/credentials.properties
    - use maven profiles : dev and prod 
- Configuration documentation 
- Add a service that returns the host url
- Check that the load balancer works
- HTTPS
- VPC
- JMeter => Long running tests and burst

