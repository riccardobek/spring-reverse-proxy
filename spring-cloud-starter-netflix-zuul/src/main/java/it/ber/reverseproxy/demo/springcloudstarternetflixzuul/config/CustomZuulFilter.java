package it.ber.reverseproxy.demo.springcloudstarternetflixzuul.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import it.ber.reverseproxy.demo.springcloudstarternetflixzuul.service.GrafanaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class CustomZuulFilter extends ZuulFilter {

    @Autowired
    private GrafanaService grafanaService;
    private static final String HEADER_PROPERTY = "X-WEBAUTH-USER";

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
        if (request.getContextPath().contains("/grafana")) {
            ctx.addZuulRequestHeader(HEADER_PROPERTY, grafanaService.getUsername());
        }
        return null;
    }
}
