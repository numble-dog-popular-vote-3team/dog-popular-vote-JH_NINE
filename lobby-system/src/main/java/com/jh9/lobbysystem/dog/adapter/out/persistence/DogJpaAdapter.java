package com.jh9.lobbysystem.dog.adapter.out.persistence;

import com.jh9.lobbysystem.dog.adapter.out.persistence.mongo.DogMongoEntity;
import com.jh9.lobbysystem.dog.adapter.out.persistence.mongo.DogMongoRepository;
import com.jh9.lobbysystem.dog.application.port.in.DogSearchCondition;
import com.jh9.lobbysystem.dog.application.port.out.persistence.DogQueryPort;
import com.jh9.lobbysystem.dog.domain.Dog;
import com.jh9.lobbysystem.utils.Adapter;
import reactor.core.publisher.Mono;

@Adapter
class DogJpaAdapter implements DogQueryPort {

    private final DogMongoRepository dogRepository;

    DogJpaAdapter(DogMongoRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Override
    public Mono<Dog> findById(Long id) {
        return dogRepository.findById(id)
            .map(DogMongoEntity::toDomain);
    }

    @Override
    public Mono<Dog> findAll(DogSearchCondition condition) {
        //TODO
        return null;
    }
}
