# Spring Reverse Proxy

[![en](https://img.shields.io/badge/lang-eng-red.svg)](./README.md)
[![it](https://img.shields.io/badge/lang-it-green.svg)](./README.it.md)

Questo progetto è un'implementazione di un reverse proxy utilizzando sia Spring Cloud Starter Netflix Zuul che Spring Cloud Starter Gateway (MVC o Webflux).

Un reverse proxy è un tipo di server che funge da intermediario tra client e server, ricevendo richieste dai client e inoltrandole ai server appropriati. Il reverse proxy può essere utilizzato per vari scopi, come il bilanciamento del carico, l'autenticazione e l'autorizzazione, la protezione da attacchi informatici, la mascheratura dell'indirizzo IP del server e la compressione del traffico. Ciò aumenta la sicurezza e migliora la scalabilità delle applicazioni online.

Questo repository contiene tre diversi progetti Spring Boot per il reverse proxy di un server Grafana:

- spring-cloud-starter-netflix-zuul
- spring-cloud-starter-gateway-mvc
- spring-cloud-starter-gateway-webflux

## Progetto 1: spring-cloud-starter-netflix-zuul

Questo progetto dimostra come utilizzare Zuul come reverse proxy in un'applicazione Spring Boot. Include un semplice esempio di instradamento delle richieste in arrivo a diversi microservizi in base al percorso URL.

La libreria spring-cloud-starter-netflix-zuul ha una limitazione legata ai web socket. L'ho superata creando un [Java WebSocket reverse proxy](https://github.com/barrett-rob/java-websocket-reverse-proxy).

## Progetto 2: spring-cloud-starter-gateway-mvc

Questo progetto mostra l'uso di Spring Cloud Gateway MVC per l'instradamento inverso in un'applicazione Spring Boot. Include esempi di instradamento delle richieste, filtraggio delle richieste e personalizzazione della risposta per le richieste in ingresso.
Anche in questo progetto si fa uso del Java WebSocket reverse proxy.

La libreria presenta dei problemi con le chiamate POST per superare questo inconveniente ho aggiunto la libreria "httpclient" per generare una RestTemplate ad hoc.

## Progetto 3: spring-cloud-starter-gateway-webflux

Questo progetto mostra l'uso di Spring Cloud Gateway WebFlux per l'instradamento inverso in un'applicazione Spring Boot. Include esempi di instradamento e filtraggio delle richieste, e personalizzazione della risposta per le richieste in ingresso utilizzando WebFlux.

## Requisiti

- Java 11 o successivo
- Docker installato sul sistema

## Esecuzione del Progetto

Per eseguire il progetto, segui questi passaggi:

1. Clona il repository del progetto sulla tua macchina locale.

    ```bash
    git clone https://github.com/riccardobek/spring-reverse-proxy.git 
    ```

2. Naviga nella directory del progetto.

    ```bash
    cd spring-reverse-proxy
    ```

3. Crea le immagini Docker del progetto.
 
    ```bash
    docker-compose up
    ```

4. Accedi alle applicazioni tramite il seguente endpoint:

    - spring-cloud-starter-netflix-zuul - [http://localhost:8080/](http://localhost:8080/)
 
        ```bash
        open http://localhost:8080/
        ```
    
    - spring-cloud-starter-gateway-mvc - [http://localhost:8081/](http://localhost:8081/)
 
        ```bash
        open http://localhost:8081clear/
        ```

    - spring-cloud-starter-gateway-webflux - [http://localhost:8082/](http://localhost:8082/)
 
        ```bash
        open http://localhost:8082/
        ```

# Licenza
 
Questo progetto è distribuito con licenza MIT. Consulta [LICENSE](LICENSE.md) per ulteriori informazioni.

# Conclusioni

Questo progetto fornisce un punto di partenza per la creazione di un reverse proxy utilizzando Zuul o Gateway in un'applicazione Spring Boot. Puoi personalizzarlo per soddisfare le tue esigenze specifiche e utilizzarlo come base per sviluppare applicazioni più complesse.