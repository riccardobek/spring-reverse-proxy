package it.ber.reverseproxy.demo.springcloudstartergateway.mvc.grafana;

import it.ber.reverseproxy.demo.springcloudstartergateway.mvc.grafana.exception.GrafanaProxyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.mvc.ProxyExchange;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
public class GrafanaService {


    @Value("${it.ber.route.grafana.url}")
    private String url;

    @Value("${it.ber.route.grafana.header.key}")
    private String headerUsername;

    @Value("${it.ber.route.grafana.header.value}")
    private String username;

    @Value("${it.ber.websocket.grafana.url}")
    private String urlWebSocket;

    @Value("${it.ber.websocket.grafana.path}")
    private String pathWebSocket;


    /**
     * In base al metodo il proxy effettua una chiamata. Nel caso delle POST in grafana non funzionano. Si è dovuto utilizzare il modulo HttpRequest
     *
     * @param proxy  proxy settato
     * @param method metodo da richiamare
     * @return Risposta della chiamata
     * @throws GrafanaProxyException Eccezione in caso di TRACE
     */
    public ResponseEntity<?> call(ProxyExchange<Object> proxy, RequestMethod method) throws GrafanaProxyException {
        return switch (method) {
            case OPTIONS -> proxy.options();
            case GET -> proxy.get();
            case PUT -> proxy.put();
            case HEAD -> proxy.head();
            case POST -> proxy.post();
            case PATCH -> proxy.patch();
            case DELETE -> proxy.delete();
            case TRACE -> throw new GrafanaProxyException("Cannot handle TRACE calls");
        };
    }



    public String getUrlGrafana() {
        return url;
    }

    public String getHeaderUsername() {
        return headerUsername;
    }

    public String getUsername() {
        return username;
    }

    public String getUrlWebSocket() {
        return urlWebSocket;
    }

    public String getPathWebSocket() {
        return pathWebSocket;
    }


}
