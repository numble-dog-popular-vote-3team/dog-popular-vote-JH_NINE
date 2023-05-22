package com.jh9.votesystem.dog.application.port.out.persistence;

import com.jh9.votesystem.dog.application.port.in.DogSearchCondition;
import com.jh9.votesystem.dog.domain.Dog;
import java.util.List;

public interface DogQueryPort {

    Dog findById(Long id);

    List<Dog> findAll();

    List<Dog> findAll(DogSearchCondition condition);
}
