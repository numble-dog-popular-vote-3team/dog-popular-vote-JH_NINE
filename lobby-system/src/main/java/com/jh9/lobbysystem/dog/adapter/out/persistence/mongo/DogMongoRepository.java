package com.jh9.lobbysystem.dog.adapter.out.persistence.mongo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DogMongoRepository extends ReactiveMongoRepository<DogMongoEntity, Long> {

}
