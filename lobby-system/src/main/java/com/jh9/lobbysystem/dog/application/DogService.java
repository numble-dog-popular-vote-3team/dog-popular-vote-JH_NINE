package com.jh9.lobbysystem.dog.application;

import com.jh9.lobbysystem.dog.application.port.in.DogSearchCondition;
import com.jh9.lobbysystem.dog.application.port.in.DogUseCase;
import com.jh9.lobbysystem.dog.application.port.out.cache.CachePort;
import com.jh9.lobbysystem.dog.application.port.out.persistence.DogQueryPort;
import com.jh9.lobbysystem.dog.domain.Dog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
public class DogService implements DogUseCase {

    private final CachePort cachePort;
    private final DogQueryPort dogQueryPort;

    public DogService(CachePort cachePort, DogQueryPort dogQueryPort) {
        this.cachePort = cachePort;
        this.dogQueryPort = dogQueryPort;
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<Dog> searchDogs(DogSearchCondition condition) {
        return dogQueryPort.findAll(condition);
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<Dog> searchDog(Long id) {
        Mono<Dog> dogMono = cachePort.get(id)
            .switchIfEmpty(getDataFromPersistence(id)
                .doOnNext(this::cacheData));

        return dogMono;
    }

    private Mono<Dog> getDataFromPersistence(Long id) {
        return dogQueryPort.findById(id);
    }

    private void cacheData(Dog dog) {
        cachePort.save(dog);
    }
}
