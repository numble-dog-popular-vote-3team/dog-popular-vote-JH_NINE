package com.jh9.cqrsworker.application.port;

import com.jh9.cqrsworker.domain.Dog;
import reactor.core.publisher.Mono;

public interface DogCommandPort {

    void saveOrUpdate(Mono<Dog> dog);
}
