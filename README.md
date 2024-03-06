# Spring Reverse Proxy

[![en](https://img.shields.io/badge/lang-eng-red.svg)](./README.md)
[![it](https://img.shields.io/badge/lang-it-green.svg)](./README.it.md)


This project is an implementation of a reverse proxy using either [Spring Cloud Starter Netflix Zuul](https://cloud.spring.io/spring-cloud-netflix/reference/html/) or [Spring Cloud Starter Gateway](https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/) (MVC or Webflux).

A reverse proxy is a type of server that acts as an intermediary between clients and servers, receiving requests from clients and forwarding them to the appropriate servers. The reverse proxy can be used for various purposes, such as load balancing, authentication and authorization, protection from cyber attacks, masking the server's IP address, and traffic compression. This increases security and improves scalability of online applications.

This repository contains three different Spring Boot projects for reverse proxying a Grafana Server:

1. spring-cloud-starter-netflix-zuul
2. spring-cloud-starter-gateway-mvc
3. spring-cloud-starter-gateway-webflux

## Project 1: spring-cloud-starter-netflix-zuul

This project demonstrates how to use Zuul as a reverse proxy in a Spring Boot application. It includes a simple example of routing incoming requests to different microservices based on the URL path.

The spring-cloud-starter-netflix-zuul library has a limitation related to web sockets. I overcame it by creating a [Java WebSocket reverse proxy](https://github.com/barrett-rob/java-websocket-reverse-proxy).

## Project 2: spring-cloud-starter-gateway-mvc

This project showcases the use of Spring Cloud Gateway MVC for reverse proxying in a Spring Boot application. It includes examples of request routing, request filtering, and customizing the response for incoming requests.
Also in that project I reused the same classes for Java WebSocket reverse proxy.

The library had a problem during the management of POST request. I overcame it by creating a RestTemplate using "httpclient".

## Project 3: spring-cloud-starter-gateway-webflux

This project demonstrates the use of Spring Cloud Gateway WebFlux for reverse proxying in a Spring Boot application. It includes examples of request routing and filtering, and customization of the response for incoming requests using WebFlux.

## Requirements

- Java 11 or later
- Docker installed on your system

## Running the Project

To run the project, follow these steps:

1. Clone the project repository to your local machine.

    ```bash
    git clone https://github.com/riccardobek/spring-reverse-proxy.git 
    ```

2. Navigate to the project directory.

    ```bash
    cd spring-reverse-proxy
    ```

3. Build the Docker images of the project.

    ```bash
    docker-compose up
    ```

4. Access the applications through the following endpoint:

    - spring-cloud-starter-netflix-zuul - [http://localhost:8080/](http://localhost:8080/)

        ```bash
        open http://localhost:8080/
        ```
    
    - spring-cloud-starter-gateway-mvc - [http://localhost:8081/](http://localhost:8081/)

        ```bash
        open http://localhost:8081clear/
        ```

    - spring-cloud-starter-gateway-webflux - [http://localhost:8082/](http://localhost:8082/)

        ```bash
        open http://localhost:8082/
        ```

# License

This project is distributed under the MIT License. See [LICENSE](./LICENSE.md) for more information.

# Conclusion

This project provides a starting point for building a reverse proxy using either Zuul or Gateway in a Spring Boot application. You can customize it to meet your specific requirements and use it as a foundation for developing more complex applications.
