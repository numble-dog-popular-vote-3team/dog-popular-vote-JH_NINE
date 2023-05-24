package com.jh9.lobbysystem.dog.adapter.out.persistence.mongo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface DogMongoRepository extends ReactiveCrudRepository<DogMongoEntity, Long> {

}
