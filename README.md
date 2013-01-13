aws-test
========

This sample application is an AWS test. The NoSQL DynamoDB services are accessed through HTTP(S) Restfull WebServices.

- aws-test-commons : common classes, just a logger guice injector for now.
    - https://github.com/Kojir0/aws-test/blob/master/aws-test-deployer/README.md
- aws-test-db : the database interfaces
- aws-test-dynamodb : the dynamodb implementation of the aws-test-db interfaces
- aws-test-rest : the REST Web services part of the project
- aws-test-integration : some integration tests
- aws-test-deployer : used to deploy the project on AWS
- aws-test-model : the POJOs used in the rest WS (GWT compatible)

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


TODO
----

- Deployer => configure path to aws credentials via system properties + readme
- HTTPS and AWS load balancer => write documentation / blog post for the whole process
- VPC
- JMeter => Long running tests and burst
- system properties => <jettyEnvXml> Optional. Location of a jetty-env.xml file, which allows you to make JNDI bindings that satisfies <env-entry>, <resource-env-ref> and <resource-ref> linkages in the web.xml. Note that these can also be made in a <jettyXml> file if you want them to apply to more than one webapp.
    - http://wiki.eclipse.org/Jetty/Feature/Jetty_Maven_Plugin
- Authentication ? already done for memeduel, it should be straightforward
