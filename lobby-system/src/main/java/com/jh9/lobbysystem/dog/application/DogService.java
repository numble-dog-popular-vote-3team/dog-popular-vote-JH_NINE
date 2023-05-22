package com.jh9.lobbysystem.dog.application;

import com.jh9.lobbysystem.dog.application.port.in.DogSearchCondition;
import com.jh9.lobbysystem.dog.application.port.in.DogUseCase;
import com.jh9.lobbysystem.dog.application.port.out.cache.CachePort;
import com.jh9.lobbysystem.dog.application.port.out.persistence.DogQueryPort;
import com.jh9.lobbysystem.dog.domain.Dog;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Dog> showCandidates(DogSearchCondition condition) {
        return dogQueryPort.findAll(condition);
    }

    @Override
    @Transactional(readOnly = true)
    public Dog showCandidate(Long id) {
        try {
            return cachePort.get(id);
        } catch (IllegalArgumentException e) {
            Dog queryedDog = dogQueryPort.findById(id);
            cachePort.save(queryedDog);
            return queryedDog;
        }
    }
}
