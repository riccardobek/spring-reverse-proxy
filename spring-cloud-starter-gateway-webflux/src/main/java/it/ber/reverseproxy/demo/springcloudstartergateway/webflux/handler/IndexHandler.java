package it.ber.reverseproxy.demo.springcloudstartergateway.webflux.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class IndexHandler {

    public Mono<ServerResponse> index(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_HTML)
                .render("index");
    }

}
