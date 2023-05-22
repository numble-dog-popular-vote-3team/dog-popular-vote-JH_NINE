package com.jh9.lobbysystem.dog.adapter.out.cache;

import com.jh9.lobbysystem.dog.application.port.out.cache.CachePort;
import com.jh9.lobbysystem.dog.domain.Dog;
import com.jh9.lobbysystem.utils.Adapter;
import java.util.Optional;

@Adapter
public class DogCacheAdapter implements CachePort {

    private final DogRedisRepository dogRedisRepository;

    public DogCacheAdapter(DogRedisRepository dogRedisRepository) {
        this.dogRedisRepository = dogRedisRepository;
    }

    @Override
    public void save(Dog dog) {
        dogRedisRepository.save(DogRedisEntity.toEntity(dog));
    }

    @Override
    public Dog get(Long id) {
        DogRedisEntity redisEntity = getOrThrow(dogRedisRepository.findById(id));
        return redisEntity.toDomain();
    }

    private <T> T getOrThrow(Optional<T> optionalT) {
        return optionalT.orElseThrow(() -> new IllegalArgumentException("There is No Data"));
    }
}
