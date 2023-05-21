package com.jh9.votesystem.dog.application.port.out.cache;

import com.jh9.votesystem.dog.domain.Dog;

public interface CachePort {

    void save(Dog dog);

    Dog get(Long id);
}
