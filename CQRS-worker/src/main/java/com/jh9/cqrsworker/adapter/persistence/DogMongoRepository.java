package com.jh9.cqrsworker.adapter.persistence;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DogMongoRepository extends ReactiveMongoRepository<DogMongoEntity, Long> {

}
