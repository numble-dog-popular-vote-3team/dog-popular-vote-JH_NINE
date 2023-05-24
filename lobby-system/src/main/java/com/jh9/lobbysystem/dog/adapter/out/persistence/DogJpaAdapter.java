package com.jh9.lobbysystem.dog.adapter.out.persistence;

import com.jh9.lobbysystem.dog.adapter.out.persistence.mongo.DogMongoEntity;
import com.jh9.lobbysystem.dog.adapter.out.persistence.mongo.DogMongoRepository;
import com.jh9.lobbysystem.dog.application.port.out.persistence.DogQueryPort;
import com.jh9.lobbysystem.dog.domain.Dog;
import com.jh9.lobbysystem.utils.Adapter;
import java.util.List;
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
    public Mono<List<Dog>> findAll(List<Long> ids) {
        return dogRepository.findAllById(ids)
            .map(DogMongoEntity::toDomain)
            .collectList();
    }
}
