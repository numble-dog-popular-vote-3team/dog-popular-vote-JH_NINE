package com.jh9.votesystem.dog.application.port.in;

import com.jh9.votesystem.dog.domain.Dog;
import java.util.List;

public interface DogUseCase {

    void createCandidate(Dog dog);

    List<Dog> showCandidates(Long lastId, int pageSize);

    Dog showCandidate(Long id);
}
