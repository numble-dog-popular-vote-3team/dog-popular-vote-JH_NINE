package com.jh9.votesystem.dog.application.port.out.eventProducer;

public interface DogChangeEventPort {

    void sendTo(String kafkaTopic, Long id);
}
