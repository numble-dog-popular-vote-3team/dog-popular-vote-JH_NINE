package com.jh9.cqrsworker.adapter.webclient;

import com.jh9.cqrsworker.application.port.DogPollingDataPort;
import com.jh9.cqrsworker.domain.Dog;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
class DogWebClientAdapter implements DogPollingDataPort {

    private static final String DOG_DATA_ENDPOINT = "/dogs";
    @Value("${dog-server.host}")
    private String host;

    @Value("${dog-server.port}")
    private int port;

    private WebClient webClient;

    @PostConstruct
    public void setUpWebClient() {
        this.webClient = WebClient.create("http://" + host + ":" +  port);
    }

    @Override
    public Mono<Dog> pollingDataById(Long id) {
        return webClient.get().uri(DOG_DATA_ENDPOINT + "/" + id).retrieve()
            .bodyToMono(Dog.class);
    }
}
