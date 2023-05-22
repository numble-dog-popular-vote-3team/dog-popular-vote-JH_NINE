package com.jh9.lobbysystem.dog.application.port.out.cache;

import com.jh9.lobbysystem.dog.domain.Dog;

public interface CachePort {

    void save(Dog dog);

    Dog get(Long id);
}
