# Spring Reverse Proxy

Following a request from my boss, I found myself studying NGINX, Apache and reverse proxies to be used between Grafana and a Spring Web App.
The simplest one I found is building a reverse proxy using NGINX. This is a standard solution, but I was looking for something that would allow me to not identify Grafana as an external application. 

Carrying out more targeted researches on reverse proxies and Spring Boot, I came across two components:

- [Spring Clound Starter Netflix Zuul](https://cloud.spring.io/spring-cloud-netflix/reference/html/)
- [Spring Cloud Gateway](https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/)

Thanks to further studies on the documentation and integration examples, I was able to create three different web application with a Grafana dedicated reverse proxy.

The code is under the [MIT lincence](./LICENSE.md) and with the necessary modifications it can allow you to create reverse proxies for other applications as well.

## Projects

### Spring Clound Starter Netflix Zuul

This component is well documented, however it has a limitation related to web sockets

Searching on github I found the [java-websocket-reverse-proxy](https://github.com/barrett-rob/java-websocket-reverse-proxy) repository of barrett-rob which after the necessary adjustments allowed me to overcome the limitation.

### Spring Cloud Gateway

This is a very good and complete component. The documentation is extensive and up to date. However, although both the MVC and WebFlux versions are provided, I think the best one is the standard one that I used in both projects: 

- [spring-cloud-gateway-mvc](./spring-cloud-starter-gateway-mvc/)
- [spring-cloud-gateway-webflux](./spring-cloud-starter-gateway-webflux/)

#### MVC

This component is based on WebFlux so I don't recommend using it with MVC. However, if you want to embark on this solution, I present what I managed to achieve to fix some of the conflicting behavior between WebFlux and MVC.

In order to use the library combined with MVC it ​​is necessary to introduce in the properties file:

    ...
    spring.main.web-application-type=reactive
    ...

This property allows you to overwrite the beans that have been declared with the same name.

#### WebFlux

As noted in the MVC section, the component is based on WebFlux. Integration and use are decidedly quick and immediate.

## Installation

TODO

