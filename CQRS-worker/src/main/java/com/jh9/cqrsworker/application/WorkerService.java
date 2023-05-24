package com.jh9.cqrsworker.application;

import com.jh9.cqrsworker.application.port.DogCommandPort;
import com.jh9.cqrsworker.application.port.DogPollingDataPort;
import com.jh9.cqrsworker.application.port.DogUpdateUseCase;
import com.jh9.cqrsworker.domain.Dog;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class WorkerService implements DogUpdateUseCase {

    private final DogPollingDataPort dogPollingDataPort;
    private final DogCommandPort dogCommandPort;

    public WorkerService(DogPollingDataPort dogPollingDataPort, DogCommandPort dogCommandPort) {
        this.dogPollingDataPort = dogPollingDataPort;
        this.dogCommandPort = dogCommandPort;
    }

    @Override
    public void update(Long id) {
        dogCommandPort.saveOrUpdate(dogPollingDataPort.pollingDataById(id));
    }
}
