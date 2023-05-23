package com.jh9.lobbysystem.dog.adapter.in.controller;

import com.jh9.lobbysystem.dog.application.port.in.DogSearchCondition;
import com.jh9.lobbysystem.dog.application.port.in.DogUseCase;
import com.jh9.lobbysystem.dog.application.port.in.VotingUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RestController
class DogController {

    private final DogUseCase dogUseCase;
    private final VotingUseCase votingUseCase;

    DogController(DogUseCase dogUseCase, VotingUseCase votingUseCase) {
        this.dogUseCase = dogUseCase;
        this.votingUseCase = votingUseCase;
    }

    @GetMapping("/dogs")
    public Mono<ServerResponse> searchDogs(
        @CookieValue(value = "dog-vote", required = false) String userCookie,
        DogSearchCondition condition) {

        return dogUseCase.searchDogs(condition)
            .map(DogsListResponse::of)
            .collectList()
            .flatMap(response -> ServerResponse.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response, DogsListResponse.class));
    }

    @GetMapping("/dogs/{id}")
    public Mono<ServerResponse> searchDog(
        @PathVariable Long id) {

        return dogUseCase.searchDog(id)
            .map(DogDetailResponse::of)
            .flatMap(response -> ServerResponse.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response, DogDetailResponse.class));
    }

    @PostMapping("/dogs/{id}/thumbsUp")
    public Mono<ServerResponse> thumbsUp(
        @PathVariable Long id) {
        return votingUseCase.thumbsUp(id)
            .then(ServerResponse.status(HttpStatus.NO_CONTENT).body(null));
    }

    @PostMapping("/dogs/{id}/thumbsDown")
    public Mono<ServerResponse> thumbsDown(
        @PathVariable Long id) {

        return votingUseCase.thumbsDown(id)
            .then(ServerResponse.status(HttpStatus.NO_CONTENT).body(null));
    }
}
