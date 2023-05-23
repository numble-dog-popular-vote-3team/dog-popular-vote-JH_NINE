package com.jh9.lobbysystem.dog.application.port.in;


import reactor.core.publisher.Mono;

public interface VotingUseCase {

    Mono<Void> thumbsUp(Long votingId);

    Mono<Void> thumbsDown(Long votingId);
}
