package it.ber.reverseproxy.demo.springcloudstarternetflixzuul.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GrafanaService {

    @Value("${it.ber.grafana.user}")
    private String username;

    @Value("${it.ber.grafana.user.header}")
    private String headerUsername;

    @Value("${it.ber.grafana.webservice.url}")
    private String webService;

    public String getUsername() {
        return username;
    }

    public String getHeaderUsername() {
        return headerUsername;
    }

    public String getWebService() {
        return webService;
    }

}
