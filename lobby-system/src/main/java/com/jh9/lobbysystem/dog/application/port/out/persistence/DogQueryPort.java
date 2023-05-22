package com.jh9.lobbysystem.dog.application.port.out.persistence;

import com.jh9.lobbysystem.dog.application.port.in.DogSearchCondition;
import com.jh9.lobbysystem.dog.domain.Dog;
import java.util.List;

public interface DogQueryPort {

    Dog findById(Long id);

    List<Dog> findAll(DogSearchCondition condition);
}
