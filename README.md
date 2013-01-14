aws-test
========

This sample application is an AWS test. The NoSQL DynamoDB services are accessed through HTTP(S) Restfull WebServices.

- aws-test-commons : common classes, just a logger guice injector for now.
    - https://github.com/Kojir0/aws-test/tree/master/aws-test-commons
- aws-test-db : the database interfaces
    - https://github.com/Kojir0/aws-test/tree/master/aws-test-db
- aws-test-dynamodb : the dynamodb implementation of the aws-test-db interfaces
    - https://github.com/Kojir0/aws-test/tree/master/aws-test-dynamodb
- aws-test-rest : the REST Web services part of the project
    - https://github.com/Kojir0/aws-test/tree/master/aws-test-rest
- aws-test-integration : some integration tests
    - https://github.com/Kojir0/aws-test/tree/master/aws-test-integration
- aws-test-deployer : used to deploy the project on AWS
    - https://github.com/Kojir0/aws-test/blob/master/aws-test-deployer
- aws-test-model : the POJOs used in the rest WS (GWT compatible)
    - https://github.com/Kojir0/aws-test-model

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

- Jetty SSL documentation
- HTTPS and AWS load balancer
- JMeter => Long running tests and burst
- system properties => <jettyEnvXml> Optional. Location of a jetty-env.xml file, which allows you to make JNDI bindings that satisfies <env-entry>, <resource-env-ref> and <resource-ref> linkages in the web.xml. Note that these can also be made in a <jettyXml> file if you want them to apply to more than one webapp.
    - http://wiki.eclipse.org/Jetty/Feature/Jetty_Maven_Plugin
- Authentication ? already done for memeduel, it should be straightforward
