FROM maven:3.8.7-openjdk-18
WORKDIR /proxy
RUN mvn clean install
ENTRYPOINT ["java","-jar","target/reverseproxy-0.0.1-SNAPSHOT.jar"]