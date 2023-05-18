package com.jh9.votesystem.dog.application;

import com.jh9.votesystem.dog.domain.Dog;
import com.jh9.votesystem.dog.infrastructure.persistence.DogRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DogService {

    private final DogRepository dogRepository;

    public DogService(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Transactional
    public void createCandidate(Dog dog) {
        dogRepository.save(dog);
    }

    @Transactional(readOnly = true)
    @Cacheable("dogs")
    public List<Dog> showCandidates(Pageable pageable) {
        return dogRepository.findAll(pageable).getContent();
    }

    public Dog showCandidate(Long id) {
        return getOrThrow(dogRepository.findById(id));
    }

    @Transactional
    public Dog thumbsUp(Long votingId) {
        Dog dog = getOrThrow(dogRepository.findById(votingId));
        dog.thumbsUp();
        return dog;
    }

    @Transactional
    public Dog thumbsDown(Long votingId) {
        Dog dog = getOrThrow(dogRepository.findById(votingId));
        dog.thumbsDown();
        return dog;
    }

    private <T> T getOrThrow(Optional<T> t) {
        return t.orElseThrow(() -> new IllegalArgumentException("NOT_FOUND DOG"));
    }
}
