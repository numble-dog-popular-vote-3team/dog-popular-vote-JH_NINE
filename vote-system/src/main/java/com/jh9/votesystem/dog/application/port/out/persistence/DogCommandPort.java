package com.jh9.votesystem.dog.application.port.out.persistence;

import com.jh9.votesystem.dog.domain.Dog;

public interface DogCommandPort {

    Dog save(Dog dog);

    Dog update(Dog dog);
}
