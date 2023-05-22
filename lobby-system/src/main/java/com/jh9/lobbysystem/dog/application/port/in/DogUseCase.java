package com.jh9.lobbysystem.dog.application.port.in;


import com.jh9.lobbysystem.dog.domain.Dog;
import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DogUseCase {

    Flux<Dog> showCandidates(DogSearchCondition dogSearchCondition);

    Mono<Dog> showCandidate(Long id);
}
