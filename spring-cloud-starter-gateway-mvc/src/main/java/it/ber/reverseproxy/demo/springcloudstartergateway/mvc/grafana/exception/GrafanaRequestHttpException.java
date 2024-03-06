package it.ber.reverseproxy.demo.springcloudstartergateway.mvc.grafana.exception;

import it.ber.reverseproxy.demo.springcloudstartergateway.mvc.exception.ApplicationException;

public class GrafanaRequestHttpException extends ApplicationException {
    public GrafanaRequestHttpException(String message) {
        super(message);
    }

    public GrafanaRequestHttpException(String message, Throwable cause) {
        super(message, cause);
    }

    public GrafanaRequestHttpException(Throwable e) {
        super(e);
    }
}
