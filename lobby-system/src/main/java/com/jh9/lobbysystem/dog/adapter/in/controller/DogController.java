package com.jh9.lobbysystem.dog.adapter.in.controller;

import com.jh9.lobbysystem.config.ZipkinLog;
import com.jh9.lobbysystem.dog.application.port.in.DogSearchCondition;
import com.jh9.lobbysystem.dog.application.port.in.DogUseCase;
import com.jh9.lobbysystem.dog.application.port.in.VotingUseCase;
import com.jh9.lobbysystem.utils.Adapter;
import com.jh9.lobbysystem.utils.MultiValueMapConverter;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Api(tags = {"검색, 투표"})
@Adapter
class DogController {

    private static final Logger log = LoggerFactory.getLogger(DogController.class);

    private final DogUseCase dogUseCase;
    private final VotingUseCase votingUseCase;

    DogController(DogUseCase dogUseCase, VotingUseCase votingUseCase) {
        this.dogUseCase = dogUseCase;
        this.votingUseCase = votingUseCase;
    }

    @ZipkinLog("searchDogs.withCondition")
    public Mono<ServerResponse> searchDogs(ServerRequest request) {
        log.info("before searchDog");
        return MultiValueMapConverter
            .mapToObject(DogSearchCondition.class, request.queryParams())
            .flatMap(dogUseCase::searchDogs) // Mono<List<Dog>>
            .map(dogs -> dogs.stream()
                .map(DogDetailResponse::of))
            .flatMap(response -> ServerResponse.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(response));
    }

    @ZipkinLog("searchDog.withID")
    public Mono<ServerResponse> searchDog(ServerRequest request) {

        log.info("before searchDog");
        Long id = Long.valueOf(request.pathVariable("id"));

        return dogUseCase.searchDog(id)
            .map(DogDetailResponse::of)
            .flatMap(response ->
                ServerResponse.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(response));
    }

    @ZipkinLog("thumbUp.withID")
    public Mono<ServerResponse> thumbsUp(ServerRequest request) {

        log.info("before searchDog");
        Long id = Long.valueOf(request.pathVariable("id"));

        return votingUseCase.thumbsUp(id)
            .then(ServerResponse.status(HttpStatus.NO_CONTENT).build());
    }

    @ZipkinLog("thumbDown.withID")
    public Mono<ServerResponse> thumbsDown(ServerRequest request) {

        log.info("before searchDog");
        Long id = Long.valueOf(request.pathVariable("id"));

        return votingUseCase.thumbsDown(id)
            .then(ServerResponse.status(HttpStatus.NO_CONTENT).build());
    }
}
