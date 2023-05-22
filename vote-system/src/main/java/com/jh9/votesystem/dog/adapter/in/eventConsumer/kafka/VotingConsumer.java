package com.jh9.votesystem.dog.adapter.in.eventConsumer.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jh9.votesystem.dog.application.port.in.DogUseCase;
import com.jh9.votesystem.dog.application.port.in.VotingUseCase;
import com.jh9.votesystem.dog.domain.Dog;
import com.jh9.votesystem.utils.Adapter;
import java.util.HashMap;
import java.util.Map;
import org.springframework.kafka.annotation.KafkaListener;

@Adapter
public class VotingConsumer {

    private final DogUseCase dogUseCase;
    private final VotingUseCase votingUseCase;

    public VotingConsumer(DogUseCase dogUseCase, VotingUseCase votingUseCase) {
        this.dogUseCase = dogUseCase;
        this.votingUseCase = votingUseCase;
    }

    @KafkaListener(topics = "order-topic")
    public void processMessage(String kafkaMessage) {
        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Dog dog = dogUseCase.findById((Long) map.get("id"));


    }
}
