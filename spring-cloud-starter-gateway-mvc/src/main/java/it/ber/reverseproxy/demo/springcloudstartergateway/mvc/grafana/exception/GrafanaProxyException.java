package it.ber.reverseproxy.demo.springcloudstartergateway.mvc.grafana.exception;

import it.ber.reverseproxy.demo.springcloudstartergateway.mvc.exception.ApplicationException;

public class GrafanaProxyException extends ApplicationException {
    public GrafanaProxyException(String message) {
        super(message);
    }

    public GrafanaProxyException(String message, Throwable cause) {
        super(message, cause);
    }

    public GrafanaProxyException(Throwable e) {
        super(e);
    }
}
