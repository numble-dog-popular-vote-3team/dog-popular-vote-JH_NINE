package com.jh9.votesystem.dog.adapter.out.eventProducer.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jh9.votesystem.dog.application.port.out.eventProducer.DogChangeEventPort;
import com.jh9.votesystem.utils.Adapter;
import org.springframework.kafka.core.KafkaTemplate;

@Adapter
public class DogChangeProducer implements DogChangeEventPort {

    private static final String VOTING_SERVER_NAME = "vote-system";
    private final KafkaTemplate<String, String> kafkaTemplate;

    public DogChangeProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendTo(String kafkaTopic, Long id) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonStringValue;
        try {
            jsonStringValue = mapper.writeValueAsString(new DogUpdateMessage(VOTING_SERVER_NAME, id));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        kafkaTemplate.send(kafkaTopic, jsonStringValue);
    }
}
