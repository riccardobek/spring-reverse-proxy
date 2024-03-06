package it.ber.reverseproxy.demo.springcloudstartergateway.mvc.grafana.websocket;

import it.ber.reverseproxy.demo.springcloudstartergateway.mvc.grafana.GrafanaService;
import it.ber.reverseproxy.demo.springcloudstartergateway.mvc.grafana.exception.GrafanaWSException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Handles establishment and tracking of next 'hop', and
 * copies data from the current session to the next hop.
 */
@Component
public class WebSocketProxyServerHandler extends AbstractWebSocketHandler {

    private final Map<String, NextHop> nextHops = new ConcurrentHashMap<>();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final GrafanaService grafanaService;

    public WebSocketProxyServerHandler(GrafanaService grafanaService) {
        this.grafanaService = grafanaService;
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        getNextHop(webSocketSession).sendMessageToNextHop(webSocketMessage);
    }

    private NextHop getNextHop(WebSocketSession webSocketSession) throws GrafanaWSException {
        NextHop nextHop = nextHops.get(webSocketSession.getId());
        if (nextHop == null) {
            logger.info("Lounched next hop");
            nextHop = new NextHop(webSocketSession, grafanaService);
            nextHops.put(webSocketSession.getId(), nextHop);
        }
        return nextHop;
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        Iterator<Map.Entry<String,NextHop>> i = nextHops.entrySet().iterator();
        while (i.hasNext()){
            Map.Entry<String,NextHop> object = i.next();
            if(!object.getValue().isOpen()){
                logger.info("Removed web socket session id: {}", object.getValue().getWebSocketSessionId());
                i.remove();
            }
        }
    }
}