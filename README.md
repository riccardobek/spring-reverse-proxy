# Spring Reverse Proxy

During my work experience I faced the problem to expose an application under the same url of a Spring application.
When I started to search the first way was using NGINX but the port will be different and that is a problem for my Project Manager so I decided to integrate a reverse proxy inside our application.

During my studies I found this two framework:

- Spring Clound Starter Netflix Zuul
- Spring Cloud Gateway

The first one has a problem with the web socket so I searched many solution the only one I found was create my own socket. I tried [Spring Clound Netflix Zuul Websocket](https://github.com/mthizo247/spring-cloud-netflix-zuul-websocket) but it doesn't work with my application.

I thanks barrett-rob for the solution of integrate a sort of [NGINX](https://github.com/barrett-rob/java-websocket-reverse-proxy) insede of java.