# Docker multi-stage build
 
# 1. Building the App with Maven
FROM maven:3.8.7-openjdk-18
 
ADD . /opt
WORKDIR /opt

RUN mvn clean install
ENTRYPOINT ["java","-jar","target/reverseproxy-0.0.2-SNAPSHOT.jar"]

