# Spring Reverse Proxy

Following a request from my Project Manager, I found myself studying NGINX, Apache and reverse proxies to be used between Grafana and a Spring Web App.
The simplest one I found is building a reverse proxy using NGINX. This is a standard solution, but I was looking for something that would allow me to not identify Grafana as an external application. 

Carrying out more targeted researches on reverse proxies and Spring Boot, I came across two components:

- [Spring Clound Starter Netflix Zuul](https://cloud.spring.io/spring-cloud-netflix/reference/html/)
- [Spring Cloud Gateway](https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/)

Thanks to further studies on the documentation and integration examples, I was able to create three different web application with a Grafana dedicated reverse proxy.

The code is under the [MIT license](./LICENSE.md) and with the necessary modifications it can allow you to create reverse proxies for other applications as well.

## Projects

### Spring Cloud Starter Netflix Zuul

This component is well documented, however it has a limitation related to web sockets

Searching on github I found the [java-websocket-reverse-proxy](https://github.com/barrett-rob/java-websocket-reverse-proxy) repository of barrett-rob which after the necessary adjustments allowed me to overcome the limitation.

### Spring Cloud Gateway

This is a very good and complete component. The documentation is extensive and up to date. However, although both the MVC and WebFlux versions are provided, I think the best one is the standard one that I used in both projects: 

- [spring-cloud-gateway-mvc](./spring-cloud-starter-gateway-mvc/)
- [spring-cloud-gateway-webflux](./spring-cloud-starter-gateway-webflux/)

#### MVC

This component is based on WebFlux so I don't recommend using it with MVC. However, if you want to embark on this solution, I present what I managed to achieve to fix some of the conflicting behavior between WebFlux and MVC.

In order to use the library combined with MVC it is necessary to introduce in the properties file:

    ...
    spring.main.web-application-type=reactive
    ...

This property allows you to overwrite the beans that have been declared with the same name.

#### WebFlux

As noted in the MVC section, the component is based on WebFlux. Integration and use are decidedly quick and immediate.

## Prerequisites

I assume you have installed Docker and it is running, JDK 11.0.6.1 and Maven. 
I used IntelliJ IDEA Community Edition for build and test the java applications.

See the [Docker website](http://www.docker.io/gettingstarted/#h_installation) for installation instructions.

See the [Oracle webiste](https://www.oracle.com/it/java/technologies/javase/jdk11-archive-downloads.html) for download the JDK.

See the [Maven website](https://maven.apache.org/) for installation instructions.

See the [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download/#section=windows) for downloading the IDE.

## Build

Steps to build the projects:

1. Clone this repo

        git clone https://github.com/riccardobek/spring-reverse-proxy.git

2. Open the terminal and run the docker compose.

        cd docker-grafana/
        docker-compose up -d

3. Open intellij and run one of the following projects:
    
    - spring-cloud-starter-netflix-zuul
    - spring-cloud-starter-gateway-mvc
    - spring-cloud-starter-gateway-webflux

It is also possible compile it in command line without intellij using this command:

        cd <project_folder>
        mvn clean install
        java -jar target/<filename>

Exemple:

        cd spring-cloud-starter-gateway-mvc
        mvn clean install
        java -jar target/spring-cloud-starter-gateway-mvc-0.0.1-SNAPSHOT.jar

6. Once everything has started up, you should be able to access the webapp on your host machine.
    
    - spring-cloud-starter-netflix-zuul - [http://localhost:8080/](http://localhost:8080/)
    
            open http://localhost:8080/

    - spring-cloud-starter-gateway-mvc - [http://localhost:8081/](http://localhost:8081/)
    
            open http://localhost:8081/

    - spring-cloud-starter-gateway-webflux - [http://localhost:8082/](http://localhost:8082/)
    
            open http://localhost:8082/

