package it.ber.reverseproxy.demo.springcloudstarternetflixzuul.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GrafanaService {

    @Value("${it.ber.grafana.user}")
    private String username;

    @Value("${it.ber.grafana.user.header}")
    private String headerUsername;

    @Value("${it.ber.grafana.websocket.url}")
    private String urlWebSocket;

    @Value("${it.ber.grafana.websocket.path}")
    private String pathWebSocket;

    public String getUsername() {
        return username;
    }

    public String getHeaderUsername() {
        return headerUsername;
    }

    public String getUrlWebSocket() {
        return urlWebSocket;
    }

    public String getPathWebSocket() {
        return pathWebSocket;
    }
}
