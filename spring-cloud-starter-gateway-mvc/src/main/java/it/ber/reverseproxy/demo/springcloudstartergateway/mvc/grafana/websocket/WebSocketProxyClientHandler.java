package it.ber.reverseproxy.demo.springcloudstartergateway.mvc.grafana.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

/**
 * Copies data from the client to the server session.
 */
public class WebSocketProxyClientHandler extends AbstractWebSocketHandler {

    private final WebSocketSession webSocketServerSession;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public WebSocketProxyClientHandler(WebSocketSession webSocketServerSession) {
        this.webSocketServerSession = webSocketServerSession;
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> webSocketMessage) throws Exception {
        try {
            logger.info("client - handleMessage: Session => {} ; Message => {}", session, webSocketMessage);
            webSocketServerSession.sendMessage(webSocketMessage);
        } catch (Exception e) {
            logger.info("client - handleMessage: Session => {} ; CLOSED", session);
            webSocketServerSession.close();
        }
    }

}