package com.jh9.votesystem.dog.application;

import com.jh9.votesystem.dog.application.port.in.VotingUseCase;
import com.jh9.votesystem.dog.application.port.out.eventProducer.DogChangeEventPort;
import com.jh9.votesystem.dog.application.port.out.persistence.DogCommandPort;
import com.jh9.votesystem.dog.application.port.out.persistence.DogQueryPort;
import com.jh9.votesystem.dog.domain.Dog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VotingService implements VotingUseCase {

    private final DogQueryPort dogQueryPort;
    private final DogCommandPort dogCommandPort;
    private final DogChangeEventPort dogChangeEventPort;

    public VotingService(DogQueryPort dogQueryPort, DogCommandPort dogCommandPort,
        DogChangeEventPort dogChangeEventPort) {
        this.dogQueryPort = dogQueryPort;
        this.dogCommandPort = dogCommandPort;
        this.dogChangeEventPort = dogChangeEventPort;
    }

    @Transactional
    public Dog thumbsUp(Long votingId) {
        Dog dog = dogQueryPort.findById(votingId);
        dog.thumbsUp();
        dogCommandPort.update(dog);
        dogChangeEventPort.sendTo("dog-change", votingId);
        return dog;
    }

    @Transactional
    public Dog thumbsDown(Long votingId) {
        Dog dog = dogQueryPort.findById(votingId);
        dog.thumbsDown();
        dogCommandPort.update(dog);
        dogChangeEventPort.sendTo("dog-change", votingId);
        return dog;
    }
}
