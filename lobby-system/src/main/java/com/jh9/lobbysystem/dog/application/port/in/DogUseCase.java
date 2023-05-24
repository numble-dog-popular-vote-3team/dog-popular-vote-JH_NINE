package com.jh9.lobbysystem.dog.application.port.in;


import com.jh9.lobbysystem.dog.domain.Dog;
import java.util.List;
import reactor.core.publisher.Mono;

public interface DogUseCase {

    Mono<List<Dog>> searchDogs(DogSearchCondition dogSearchCondition);

    Mono<Dog> searchDog(Long id);
}
