package it.ber.reverseproxy.demo.springcloudstartergateway.mvc.grafana;

import it.ber.reverseproxy.demo.springcloudstartergateway.mvc.grafana.exception.GrafanaProxyException;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.mvc.ProxyExchange;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

@RestController
public class GrafanaController {

    @Autowired
    private GrafanaService grafanaService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private RestTemplate restTemplate;


    /**
     * Consente di servire tutte le pagine di grafana
     *
     * @param proxy              permette la creazione di un reverse proxy per tutte le richieste (escluso le POST)
     * @param httpServletRequest contiene gli header della richiesta a proxy o POST
     * @param httpEntity         contiene il body delle richieste a proxy o POST
     * @return
     * @throws GrafanaProxyException Le eccezzioni da lanciare sono per un percorso non ammesso
     */
    @RequestMapping({"/grafana/**"})
    @ResponseBody
    public ResponseEntity<?> proxy(ProxyExchange<Object> proxy,
                                   HttpServletRequest httpServletRequest,
                                   HttpEntity<String> httpEntity) throws GrafanaProxyException {

        //MAPPING degli HEADER
        HttpHeaders headers = new HttpHeaders();
        headers.set(grafanaService.getHeaderUsername(), grafanaService.getUsername());
        for (Iterator<String> it = httpServletRequest.getHeaderNames().asIterator(); it.hasNext(); ) {
            String header = it.next();
            headers.add(header, httpServletRequest.getHeader(header));
        }
        proxy.headers(headers);

        //Estrapolo percorso
        String path = proxy.path("/grafana");

        try {

            URI uriBuilder = new URI(grafanaService.getUrlGrafana() + path);

            if (RequestMethod.valueOf(httpServletRequest.getMethod()).equals(RequestMethod.POST)) {
                //Le chiamate POST non funzionano tramite proxy
                RestTemplate template = restTemplate();


                HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
                return template.postForObject(uriBuilder.toString(), requestEntity, ResponseEntity.class);
            }

            proxy.uri(uriBuilder);
            return grafanaService.call(proxy, RequestMethod.valueOf(httpServletRequest.getMethod()));

        } catch (Exception e) {
            return null;
        }

    }

    private RestTemplate restTemplate()
            throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {

        if(restTemplate == null) {
            TrustStrategy acceptingTrustStrategy = (x509Certificates, s) -> true;
            SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                    .loadTrustMaterial(null, acceptingTrustStrategy).build();

            SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());

            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();

            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

            requestFactory.setHttpClient(httpClient);

            restTemplate = new RestTemplate(requestFactory);
        }

        return restTemplate;
    }


}
