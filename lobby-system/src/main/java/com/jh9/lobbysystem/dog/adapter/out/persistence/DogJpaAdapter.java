package com.jh9.lobbysystem.dog.adapter.out.persistence;

import com.jh9.lobbysystem.dog.adapter.out.persistence.mongo.DogMongoEntity;
import com.jh9.lobbysystem.dog.adapter.out.persistence.mongo.DogMongoRepository;
import com.jh9.lobbysystem.dog.application.port.in.DogSearchCondition;
import com.jh9.lobbysystem.dog.application.port.out.persistence.DogQueryPort;
import com.jh9.lobbysystem.dog.domain.Dog;
import com.jh9.lobbysystem.utils.Adapter;
import java.util.List;
import java.util.Optional;

@Adapter
class DogJpaAdapter implements DogQueryPort {

    private final DogMongoRepository dogRepository;

    DogJpaAdapter(DogMongoRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Override
    public Dog findById(Long id) {
        DogMongoEntity findEntity = getOrThrow(dogRepository.findById(id));
        //TODO
    }

    @Override
    public List<Dog> findAll(DogSearchCondition condition) {
        //TODO
        return null;
    }

    private <T> T getOrThrow(Optional<T> optionalT) {
        return optionalT.orElseThrow(
            () -> new IllegalArgumentException("There is no data")
        );
    }
}
