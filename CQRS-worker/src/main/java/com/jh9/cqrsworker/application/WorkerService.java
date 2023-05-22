package com.jh9.cqrsworker.application;

import com.jh9.cqrsworker.application.port.DogCommandPort;
import com.jh9.cqrsworker.application.port.DogPollingDataPort;
import com.jh9.cqrsworker.application.port.DogUpdateUseCase;
import org.springframework.stereotype.Service;

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
