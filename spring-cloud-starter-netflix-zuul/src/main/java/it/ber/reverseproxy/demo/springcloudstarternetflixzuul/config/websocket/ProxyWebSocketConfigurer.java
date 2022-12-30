package it.ber.reverseproxy.demo.springcloudstarternetflixzuul.config.websocket;

import it.ber.reverseproxy.demo.springcloudstarternetflixzuul.service.GrafanaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class ProxyWebSocketConfigurer implements WebSocketConfigurer {

    @Autowired
    private GrafanaService grafanaService;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new WebSocketProxyServerHandler(grafanaService), grafanaService.getPathWebSocket());
    }


}