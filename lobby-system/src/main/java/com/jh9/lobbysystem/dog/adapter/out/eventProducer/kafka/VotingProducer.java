package com.jh9.lobbysystem.dog.adapter.out.eventProducer.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jh9.lobbysystem.dog.application.port.out.eventProducer.SendVotingEventPort;
import com.jh9.lobbysystem.utils.Adapter;
import org.springframework.kafka.core.KafkaTemplate;
import reactor.core.publisher.Mono;

@Adapter
public class VotingProducer implements SendVotingEventPort {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public VotingProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public Mono<Void> sendTo(String kafkaTopic, Long id, boolean isThumbUp) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonStringValue = "";
        try {
            jsonStringValue = mapper.writeValueAsString(new VotingDto(id, isThumbUp));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        kafkaTemplate.send(kafkaTopic, jsonStringValue);

        return Mono.empty();
    }
}
