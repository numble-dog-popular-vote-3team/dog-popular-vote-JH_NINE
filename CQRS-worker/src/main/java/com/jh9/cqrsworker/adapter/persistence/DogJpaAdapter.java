package com.jh9.cqrsworker.adapter.persistence;

import com.jh9.cqrsworker.application.port.DogCommandPort;
import com.jh9.cqrsworker.domain.Dog;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
class DogJpaAdapter implements DogCommandPort {

    private final DogMongoRepository dogRepository;

    DogJpaAdapter(DogMongoRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Override
    public void saveOrUpdate(Mono<Dog> dog) {
        dog.map(DogMongoEntity::toEntity)
            .subscribe(dogRepository::save);
    }
}
