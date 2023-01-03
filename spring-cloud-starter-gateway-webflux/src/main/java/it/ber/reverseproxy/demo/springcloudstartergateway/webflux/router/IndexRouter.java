package it.ber.reverseproxy.demo.springcloudstartergateway.webflux.router;

import it.ber.reverseproxy.demo.springcloudstartergateway.webflux.handler.IndexHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class IndexRouter {

    @Bean
    public RouterFunction<ServerResponse> route(IndexHandler indexHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/")
                        .and(RequestPredicates.accept(MediaType.TEXT_HTML)), indexHandler::index);
    }
}
