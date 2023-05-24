package com.jh9.lobbysystem.dog.application.port.out.persistence;

import com.jh9.lobbysystem.dog.domain.Dog;
import java.util.List;
import reactor.core.publisher.Mono;

public interface DogQueryPort {

    Mono<Dog> findById(Long id);

    Mono<List<Dog>> findAll(List<Long> ids);
}
