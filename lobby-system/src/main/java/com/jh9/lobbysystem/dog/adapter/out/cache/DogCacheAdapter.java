package com.jh9.lobbysystem.dog.adapter.out.cache;

import com.jh9.lobbysystem.dog.application.port.out.cache.CachePort;
import com.jh9.lobbysystem.dog.domain.Dog;
import com.jh9.lobbysystem.utils.Adapter;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import reactor.core.publisher.Mono;

@Adapter
public class DogCacheAdapter implements CachePort {

    private final ReactiveRedisTemplate<Long, Dog> redisTemplate;

    public DogCacheAdapter(ReactiveRedisTemplate<Long, Dog> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Mono<Boolean> save(Dog dog) {
        return redisTemplate.opsForValue()
            .set(dog.id(), dog);
    }

    @Override
    public Mono<Dog> get(Long id) {
        return redisTemplate.opsForValue()
            .get(id);
    }
}
