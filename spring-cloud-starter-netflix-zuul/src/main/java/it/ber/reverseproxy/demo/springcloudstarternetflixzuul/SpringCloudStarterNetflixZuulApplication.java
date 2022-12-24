package it.ber.reverseproxy.demo.springcloudstarternetflixzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class SpringCloudStarterNetflixZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudStarterNetflixZuulApplication.class, args);
    }

}
