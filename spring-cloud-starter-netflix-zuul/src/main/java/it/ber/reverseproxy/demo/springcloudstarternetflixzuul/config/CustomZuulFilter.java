package it.ber.reverseproxy.demo.springcloudstarternetflixzuul.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class CustomZuulFilter extends ZuulFilter {

    @Value("${it.ber.grafana.user}")
    private String username;

    private static  final String HEADER_PROPERTY = "X-WEBAUTH-USER";

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        if (request.getContextPath().contains("/grafana")){
            ctx.addZuulRequestHeader(HEADER_PROPERTY, username);
        }
        return null;
    }
}
