package com.jh9.lobbysystem.dog.application.port.out.eventProducer;

public interface SendVotingEventPort {

    void sendTo(String kafkaTopic, Long id, boolean isThumbUp);
}
