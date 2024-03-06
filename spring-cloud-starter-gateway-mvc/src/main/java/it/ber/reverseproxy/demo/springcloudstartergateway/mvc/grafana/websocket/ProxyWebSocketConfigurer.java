package it.ber.reverseproxy.demo.springcloudstartergateway.mvc.grafana.websocket;

import it.ber.reverseproxy.demo.springcloudstartergateway.mvc.grafana.GrafanaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class ProxyWebSocketConfigurer implements WebSocketConfigurer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private GrafanaService grafanaService;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        logger.info(grafanaService.getPathWebSocket());
        registry.addHandler(new WebSocketProxyServerHandler(grafanaService), grafanaService.getPathWebSocket());
    }


}