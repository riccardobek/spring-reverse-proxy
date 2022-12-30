package it.ber.reverseproxy.demo.springcloudstarternetflixzuul.config.websocket;

import it.ber.reverseproxy.demo.springcloudstarternetflixzuul.service.GrafanaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * Represents a 'hop' in the proxying chain, establishes a 'client' to
 * communicate with the next server, with a {@link WebSocketProxyClientHandler}
 * to copy data from the 'client' to the supplied 'server' session.
 */
public class NextHop {

    private final WebSocketSession webSocketClientSession;

    public NextHop(WebSocketSession webSocketServerSession, GrafanaService grafanaService) {
        webSocketClientSession = createWebSocketClientSession(webSocketServerSession, grafanaService);
    }

    private WebSocketSession createWebSocketClientSession(WebSocketSession webSocketServerSession, GrafanaService grafanaService) {
        try {
            WebSocketHttpHeaders webSocketHttpHeaders = new WebSocketHttpHeaders();
            webSocketHttpHeaders.put(grafanaService.getHeaderUsername(), Collections.singletonList(grafanaService.getUsername()));
            return new StandardWebSocketClient()
                    .doHandshake(new WebSocketProxyClientHandler(webSocketServerSession), webSocketHttpHeaders, new URI(grafanaService.getUrlWebSocket()))
                    .get(1000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessageToNextHop(WebSocketMessage<?> webSocketMessage) throws IOException {
        webSocketClientSession.sendMessage(webSocketMessage);
    }

    public boolean isOpen(){
        return webSocketClientSession.isOpen();
    }

    public String getWebSocketSessionId(){
        return webSocketClientSession.getId();
    }
}