package com.jh9.cqrsworker.application.port;

import com.jh9.cqrsworker.domain.Dog;
import com.jh9.cqrsworker.util.PollingDataPort;

public interface DogPollingDataPort extends PollingDataPort<Dog> {

}
