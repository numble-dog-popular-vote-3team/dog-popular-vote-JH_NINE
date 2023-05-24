package com.jh9.cqrsworker.adapter.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jh9.cqrsworker.application.port.DogUpdateUseCase;
import java.util.HashMap;
import java.util.Map;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class DogUpdateConsumer {

    private final DogUpdateUseCase dogUpdateUseCase;

    public DogUpdateConsumer(DogUpdateUseCase dogUpdateUseCase) {
        this.dogUpdateUseCase = dogUpdateUseCase;
    }

    @KafkaListener(topics = "dog-change")
    public void consume(String kafkaMessage) {
        DogUpdateMessage message;
        ObjectMapper mapper = new ObjectMapper();
        try {
            message = mapper.readValue(kafkaMessage, DogUpdateMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        dogUpdateUseCase.update(message.id());
    }
}
