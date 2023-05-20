package com.jh9.votesystem.dog.application;

import com.jh9.votesystem.dog.application.port.in.DogUseCase;
import com.jh9.votesystem.dog.application.port.out.persistence.DogCommandPort;
import com.jh9.votesystem.dog.application.port.out.persistence.DogQueryPort;
import com.jh9.votesystem.dog.domain.Dog;
import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DogService implements DogUseCase {

    private final DogCommandPort dogCommandPort;
    private final DogQueryPort dogQueryPort;

    public DogService(DogCommandPort dogCommandPort, DogQueryPort dogQueryPort) {
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
    public List<Dog> showCandidates(Long lastId, int pageSize) {
        return dogQueryPort.findAll(lastId, pageSize);
    }

    @Override

    @Transactional(readOnly = true)
    @Cacheable("dog")
    public Dog showCandidate(Long id) {
        return dogQueryPort.findById(id);
    }
}
