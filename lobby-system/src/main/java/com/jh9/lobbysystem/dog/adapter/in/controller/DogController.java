package com.jh9.lobbysystem.dog.adapter.in.controller;

import com.jh9.lobbysystem.dog.application.port.in.DogSearchCondition;
import com.jh9.lobbysystem.dog.application.port.in.DogUseCase;
import com.jh9.lobbysystem.dog.application.port.in.VotingUseCase;
import com.jh9.lobbysystem.dog.domain.Dog;
import com.jh9.lobbysystem.utils.Adapter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Adapter
class DogController {

    private final DogUseCase dogUseCase;
    private final VotingUseCase votingUseCase;

    DogController(DogUseCase dogUseCase, VotingUseCase votingUseCase) {
        this.dogUseCase = dogUseCase;
        this.votingUseCase = votingUseCase;
    }

    public Mono<ServerResponse> searchDogs(ServerRequest request) {

        return request.bodyToMono(DogSearchCondition.class)
            .flatMap(dogUseCase::searchDogs)
            .map(DogsListResponse::of)
            .flatMap(response -> ServerResponse.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(response));
    }

    public Mono<ServerResponse> searchDog(ServerRequest request) {

        Long id = Long.valueOf(request.pathVariable("id"));

        return dogUseCase.searchDog(id)
            .map(DogDetailResponse::of)
            .flatMap(response ->
                ServerResponse.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(response));
    }

    public Mono<ServerResponse> thumbsUp(ServerRequest request) {

        Long id = Long.valueOf(request.pathVariable("id"));

        return votingUseCase.thumbsUp(id)
            .then(ServerResponse.status(HttpStatus.NO_CONTENT).build());
    }

    public Mono<ServerResponse> thumbsDown(ServerRequest request) {

        Long id = Long.valueOf(request.pathVariable("id"));

        return votingUseCase.thumbsDown(id)
            .then(ServerResponse.status(HttpStatus.NO_CONTENT).build());
    }
}
