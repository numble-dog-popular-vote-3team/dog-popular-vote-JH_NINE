package com.jh9.votesystem.dog.adapter.out.persistence.jpa;

import com.jh9.votesystem.dog.application.port.out.persistence.DogCommandPort;
import com.jh9.votesystem.dog.application.port.out.persistence.DogQueryPort;
import com.jh9.votesystem.dog.domain.Dog;
import com.jh9.votesystem.utils.Adapter;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

@Adapter
class DogJpaAdapter implements DogQueryPort, DogCommandPort {

    private final DogJpaRepository dogRepository;

    public DogJpaAdapter(DogJpaRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Override
    public Dog save(Dog dog) {
        DogJpaEntity savedEntity = dogRepository.save(DogJpaEntity.toEntity(dog));
        return savedEntity.toDomain();
    }

    @Override
    public Dog update(Dog dog) {
        DogJpaEntity updatedEntity = dogRepository.save(DogJpaEntity.toEntity(dog));
        return updatedEntity.toDomain();
    }

    @Override
    public Dog findById(Long id) {
        DogJpaEntity findEntity = getOrThrow(dogRepository.findById(id));
        return findEntity.toDomain();
    }

    @Override
    public List<Dog> findAll() {
        return dogRepository.findAll().stream()
            .map(DogJpaEntity::toDomain)
            .toList();
    }

    @Override
    public List<Dog> findAll(Pageable pageable) {
        return dogRepository.findAll(pageable).stream()
            .map(DogJpaEntity::toDomain)
            .toList();
    }

    private <T> T getOrThrow(Optional<T> optionalT) {
        return optionalT.orElseThrow(
            () -> new IllegalArgumentException("There is no data")
        );
    }
}
