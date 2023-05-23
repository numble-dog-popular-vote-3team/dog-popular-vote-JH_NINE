package com.jh9.lobbysystem.dog.application.port.out.eventProducer;

import reactor.core.publisher.Mono;

public interface SendVotingEventPort {

    Mono<Void> sendTo(String kafkaTopic, Long id, boolean isThumbUp);
}
