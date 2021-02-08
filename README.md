## Microservice
Spring Boot 2.0 App

### Table of Contents
* [Summary](#summary)
* [Requirements](#requirements)
* [Configuration](#configuration)
* [Project contents](#project-contents)
* [Run](#run)

### Summary

This is an example how to use Apache Kafka Client with a Spring Boot Application, there are two main classes, the Sender, and the Receiver which are used to Produce and Consume AVRO Kafka Message, this implementation is using Azure Event Hub as Streaming Platform and the SASL_SSL security protocol.

There is a Rest Service used to send a message to the Kafka Server and also there is a KafkaListener class which is receiving all the messages published on one topic.


### Requirements
* Azure Event Hub https://docs.microsoft.com/es-es/azure/event-hubs/event-hubs-for-kafka-ecosystem-overview.
* [Maven](https://maven.apache.org/install.html)
* Java 8: Any compliant JVM should work.
  * [Java 8 JDK from Oracle](http://www.oracle.com/technetwork/java/javase/downloads/index.html)


### Configuration
Capabilities are provided through dependencies in the pom.xml file and the application.properties for the main properties.

### Project contents
The ports are set to the defaults of 8080 for http.

The Kafka configuration properties are in application.properties, there you can find all the CloudKarafka credentials you need full fill in order to configure the access to the Kafka Server

### Run

To build and run the application:
1. `mvn clean compile install`
1. `java -jar ./target/kafka-connector-0.0.1-SNAPSHOT.jar`

### Endpoints

The application exposes the following endpoints:
* Kafka Connector endpoint: `<host>:<port>/kafka/produce` 
  
  e.g.: 
  `curl --location --request POST 'http://localhost:9090/kafka/produce/avro' --header 'Content-Type: text/plain'  --data-raw 'Mensaje de Prueba Farith Heras'`


The Consumer is logging in the console all the received messages 
