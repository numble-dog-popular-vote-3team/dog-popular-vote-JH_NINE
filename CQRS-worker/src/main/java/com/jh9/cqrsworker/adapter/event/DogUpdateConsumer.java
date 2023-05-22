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

    @KafkaListener(topics = "dog-change-topic")
    public void consume(String kafkaMessage) {
        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        dogUpdateUseCase.update((Long) map.get("id"));
    }
}
