package com.jh9.lobbysystem.dog.application;

import com.jh9.lobbysystem.dog.application.port.in.DogSearchCondition;
import com.jh9.lobbysystem.dog.application.port.in.DogUseCase;
import com.jh9.lobbysystem.dog.application.port.out.cache.CachePort;
import com.jh9.lobbysystem.dog.application.port.out.persistence.DogQueryPort;
import com.jh9.lobbysystem.dog.application.port.out.webClient.DogSearchPort;
import com.jh9.lobbysystem.dog.domain.Dog;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
public class DogService implements DogUseCase {

    private final CachePort cachePort;
    private final DogQueryPort dogQueryPort;
    private final DogSearchPort dogSearchPort;

    public DogService(CachePort cachePort, DogQueryPort dogQueryPort, DogSearchPort dogSearchPort) {
        this.cachePort = cachePort;
        this.dogQueryPort = dogQueryPort;
        this.dogSearchPort = dogSearchPort;
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<List<Dog>> searchDogs(DogSearchCondition condition) {
        return dogSearchPort.searchByCondition(condition)
            .flatMap(dogQueryPort::findAll);
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<Dog> searchDog(Long id) {
        return cachePort.get(id)
            .switchIfEmpty(getDataFromPersistence(id)
                .doOnNext(this::cacheData));
    }

    private Mono<Dog> getDataFromPersistence(Long id) {
        return dogQueryPort.findById(id);
    }

    private void cacheData(Dog dog) {
        cachePort.save(dog);
    }
}
