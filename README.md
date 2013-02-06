aws-test
========

This sample application is an AWS test. The NoSQL DynamoDB services are accessed through HTTP(S) Restfull WebServices.

- aws-test-commons : common classes, just a logger guice injector for now.
- aws-test-db : the database interfaces
- aws-test-dynamodb : the dynamodb implementation of the aws-test-db interfaces
- **aws-test-rest : the REST Web services part of the project**
- aws-test-integration : some integration tests
- **aws-test-deployer : used to deploy the project on AWS**
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
