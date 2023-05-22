package com.jh9.lobbysystem.dog.application.port.out.persistence;

import com.jh9.lobbysystem.dog.application.port.in.DogSearchCondition;
import com.jh9.lobbysystem.dog.domain.Dog;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DogQueryPort {

    Mono<Dog> findById(Long id);

    Flux<Dog> findAll(DogSearchCondition condition);
}
