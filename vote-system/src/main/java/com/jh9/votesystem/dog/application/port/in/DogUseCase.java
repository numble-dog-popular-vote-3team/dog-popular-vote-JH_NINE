package com.jh9.votesystem.dog.application.port.in;

import com.jh9.votesystem.dog.domain.Dog;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface DogUseCase {

    void createCandidate(Dog dog);

    List<Dog> showCandidates(Pageable pageable);

    Dog showCandidate(Long id);
}
