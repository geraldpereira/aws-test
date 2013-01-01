aws-test
========

This sample application is an AWS test. The NoSQL DynamoDB services are accessed through HTTP(S) Restfull WebServices.

Tehnos :
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
  - sudo yum upgrade
- Install JDK 1.7 // Optionnal for tomcat ? for jetty ?
  - sudo yum install java-1.7.0-openjdk-devel
  - sudo yum remove java-1.6.0-openjdk

- For jetty :
  - Download and extract http://wiki.eclipse.org/Jetty/Starting/Downloads
  - Install as a service http://jawher.net/2009/12/18/manually-installing-a-recent-version-of-jetty-as-a-service-in-linux/
  - Configure server for high load http://wiki.eclipse.org/Jetty/Howto/High_Load
  - Configure Iptables http://www.eclipse.org/jetty/documentation/current/setting-port80-access.html
    sudo iptables -t nat -I PREROUTING -p tcp --dport 80 -j REDIRECT --to-port 8080
    sudo service iptables save
    sudo service iptables restart
  - Upload war to $JETTY_HOME/webapps
  - Restart jetty 'service jetty restart'

TODO
- HTTPS
- Load balancer
- JMeter => Tomcat vs Jetty perf (mock DynamoDB to stay in the AWS free tier)

