package com.jh9.lobbysystem.dog.adapter.out.webClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jh9.lobbysystem.dog.application.port.in.DogSearchCondition;
import com.jh9.lobbysystem.dog.application.port.out.webClient.DogSearchPort;
import com.jh9.lobbysystem.utils.MultiValueMapConverter;
import jakarta.annotation.PostConstruct;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
class DogWebClientAdapter implements DogSearchPort {

    private static final String DOG_DATA_ENDPOINT = "/dogs";
    @Value("${dog-server.host}")
    private String host;

    @Value("${dog-server.port}")
    private int port;

    private WebClient webClient;
    private ObjectMapper objectMapper;

    @PostConstruct
    public void setUpWebClient() {
        objectMapper = new ObjectMapper();
        this.webClient = WebClient.create("http://" + host + ":" +  port);
    }

    @Override
    public Mono<List<Long>> searchByCondition(DogSearchCondition condition) {
        return webClient.get().uri(uriBuilder ->
                uriBuilder.path(DOG_DATA_ENDPOINT)
                    .queryParams(MultiValueMapConverter.convert(objectMapper, condition))
                    .build())
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<List<DogResponse>>() {
            })
            .map(dogResponses -> dogResponses
                .stream()
                .map(DogResponse::id)
                .toList()
            );
    }

}
