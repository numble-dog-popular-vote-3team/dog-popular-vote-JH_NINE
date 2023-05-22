package com.jh9.lobbysystem.dog.application.port.in;


import com.jh9.lobbysystem.dog.domain.Dog;
import java.util.List;

public interface DogUseCase {

    List<Dog> showCandidates(DogSearchCondition dogSearchCondition);

    Dog showCandidate(Long id);
}
