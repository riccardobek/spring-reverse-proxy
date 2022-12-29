package it.ber.reverseproxy.demo.springcloudstarternetflixzuul.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import it.ber.reverseproxy.demo.springcloudstarternetflixzuul.service.GrafanaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableZuulProxy
public class CustomZuulFilter extends ZuulFilter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private GrafanaService grafanaService;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        if (request.getRequestURL().toString().contains("/grafana/")) {
            ctx.addZuulRequestHeader(grafanaService.getHeaderUsername(), grafanaService.getUsername());
        }
        return null;
    }
}
