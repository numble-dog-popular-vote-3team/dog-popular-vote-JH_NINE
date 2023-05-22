package com.jh9.votesystem.dog.application;

import com.jh9.votesystem.dog.application.port.in.DogSearchCondition;
import com.jh9.votesystem.dog.application.port.in.DogUseCase;
import com.jh9.votesystem.dog.application.port.out.cache.CachePort;
import com.jh9.votesystem.dog.application.port.out.persistence.DogCommandPort;
import com.jh9.votesystem.dog.application.port.out.persistence.DogQueryPort;
import com.jh9.votesystem.dog.domain.Dog;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DogService implements DogUseCase {

    private final CachePort cachePort;
    private final DogCommandPort dogCommandPort;
    private final DogQueryPort dogQueryPort;

    public DogService(CachePort cachePort, DogCommandPort dogCommandPort, DogQueryPort dogQueryPort) {
        this.cachePort = cachePort;
        this.dogCommandPort = dogCommandPort;
        this.dogQueryPort = dogQueryPort;
    }

    @Override
    @Transactional
    public void createCandidate(Dog dog) {
        dogCommandPort.save(dog);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Dog> findByCondition(DogSearchCondition condition) {
        return dogQueryPort.findAll(condition);
    }

    @Override
    @Transactional(readOnly = true)
    public Dog findById(Long id) {
        try {
            return cachePort.get(id);
        } catch (IllegalArgumentException e) {
            Dog queryedDog = dogQueryPort.findById(id);
            cachePort.save(queryedDog);
            return queryedDog;
        }
    }
}
