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
  - sudo install java-1.7.0-openjdk
  - sudo yum remove java-1.6.0-openjdk

- For jetty :
  - Download and extract http://wiki.eclipse.org/Jetty/Starting/Downloads
  - Install as a service http://jawher.net/2009/12/18/manually-installing-a-recent-version-of-jetty-as-a-service-in-linux/

- For tomcat :
  - sudo yum install tomcat7

- TODO Upload war, create image

TODO
- HTTPS
- Load balancer
- JMeter => Tomcat vs Jetty perf (mock DynamoDB to stay in the AWS free tier)

