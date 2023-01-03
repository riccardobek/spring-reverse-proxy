package it.ber.reverseproxy.demo.springcloudstartergateway.config;

import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.socket.client.TomcatWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import org.springframework.web.reactive.socket.server.RequestUpgradeStrategy;
import org.springframework.web.reactive.socket.server.upgrade.TomcatRequestUpgradeStrategy;
import reactor.netty.http.client.HttpClient;

@Configuration
public class ReverseProxyConfig {

    @Bean
    @Primary
    public RequestUpgradeStrategy requestUpgradeStrategy() {
        return new TomcatRequestUpgradeStrategy();
    }

    @Bean
    @Primary
    WebSocketClient tomcatWebSocketClient() {
        return new TomcatWebSocketClient();
    }

    @Bean
    public HttpClient httpClient() {
        return HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
    }
}
