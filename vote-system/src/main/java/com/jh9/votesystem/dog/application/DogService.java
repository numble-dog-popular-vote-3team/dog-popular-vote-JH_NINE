package com.jh9.votesystem.dog.application;

import com.jh9.votesystem.dog.application.port.in.DogSearchCondition;
import com.jh9.votesystem.dog.application.port.in.DogUseCase;
import com.jh9.votesystem.dog.application.port.out.persistence.DogQueryPort;
import com.jh9.votesystem.dog.domain.Dog;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DogService implements DogUseCase {

    private static final Logger log = LoggerFactory.getLogger(DogService.class);
    private final DogQueryPort dogQueryPort;

    public DogService(DogQueryPort dogQueryPort) {
        this.dogQueryPort = dogQueryPort;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Long> findByCondition(DogSearchCondition condition) {
        log.info("find Dog By Condition");
        return dogQueryPort.findByCondition(condition);
    }

    @Override
    @Transactional(readOnly = true)
    public Dog findById(Long id) {
        log.info("find Dog By Id");
        return dogQueryPort.findById(id);
    }
}
