package com.jh9.lobbysystem.dog.adapter.out.persistence.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DogMongoRepository extends MongoRepository<DogMongoEntity, Long> {

}
