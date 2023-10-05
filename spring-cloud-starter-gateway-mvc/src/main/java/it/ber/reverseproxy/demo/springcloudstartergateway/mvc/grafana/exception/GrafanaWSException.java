package it.ber.reverseproxy.demo.springcloudstartergateway.mvc.grafana.exception;

import it.ber.reverseproxy.demo.springcloudstartergateway.mvc.exception.ApplicationException;

public class GrafanaWSException extends ApplicationException {

    public GrafanaWSException(String message) {
        super(message);
    }

    public GrafanaWSException(String message, Throwable cause) {
        super(message, cause);
    }

    public GrafanaWSException(Throwable e) {
        super(e);
    }
}
