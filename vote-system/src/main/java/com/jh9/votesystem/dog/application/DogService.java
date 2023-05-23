package com.jh9.votesystem.dog.application;

import com.jh9.votesystem.dog.application.port.in.DogSearchCondition;
import com.jh9.votesystem.dog.application.port.in.DogUseCase;
import com.jh9.votesystem.dog.application.port.out.persistence.DogQueryPort;
import com.jh9.votesystem.dog.domain.Dog;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DogService implements DogUseCase {

    private final DogQueryPort dogQueryPort;

    public DogService(DogQueryPort dogQueryPort) {
        this.dogQueryPort = dogQueryPort;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Long> findByCondition(DogSearchCondition condition) {
        return dogQueryPort.findByCondition(condition);
    }

    @Override
    @Transactional(readOnly = true)
    public Dog findById(Long id) {
        return dogQueryPort.findById(id);
    }
}
