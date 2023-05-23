package com.jh9.lobbysystem.dog.application.port.out.cache;

import com.jh9.lobbysystem.dog.domain.Dog;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CachePort {

    Mono<Boolean> save(Dog dog);

    Mono<Dog> get(Long id);
}
