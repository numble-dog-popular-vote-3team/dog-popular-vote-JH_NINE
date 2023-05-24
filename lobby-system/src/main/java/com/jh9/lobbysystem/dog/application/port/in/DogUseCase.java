package com.jh9.lobbysystem.dog.application.port.in;


import com.jh9.lobbysystem.dog.domain.Dog;
import reactor.core.publisher.Mono;

public interface DogUseCase {

    Mono<Dog> searchDogs(DogSearchCondition dogSearchCondition);

    Mono<Dog> searchDog(Long id);
}
