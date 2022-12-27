package it.ber.reverseproxy.demo.springcloudstarternetflixzuul.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GrafanaService {

    @Value("${it.ber.grafana.user}")
    private String username;

    public String getUsername() {
        return username;
    }
}
