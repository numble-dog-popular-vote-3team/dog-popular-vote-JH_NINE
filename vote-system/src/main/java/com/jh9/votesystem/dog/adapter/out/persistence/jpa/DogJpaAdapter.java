package com.jh9.votesystem.dog.adapter.out.persistence.jpa;

import com.jh9.votesystem.dog.application.port.in.DogSearchCondition;
import com.jh9.votesystem.dog.application.port.out.persistence.DogCommandPort;
import com.jh9.votesystem.dog.application.port.out.persistence.DogQueryPort;
import com.jh9.votesystem.dog.domain.Dog;
import com.jh9.votesystem.utils.Adapter;
import java.util.List;

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
        return dogRepository.findById(id)
            .map(DogJpaEntity::toDomain)
            .orElseThrow(() -> new IllegalArgumentException("There is no data"));
    }

    @Override
    public List<Long> findByCondition(DogSearchCondition condition) {
        return dogRepository.search(condition);
    }
}
