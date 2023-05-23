package com.jh9.votesystem.dog.adapter.in.eventConsumer.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jh9.votesystem.dog.application.port.in.VotingUseCase;
import com.jh9.votesystem.utils.Adapter;
import java.util.HashMap;
import java.util.Map;
import org.springframework.kafka.annotation.KafkaListener;

@Adapter
public class VotingConsumer {

    private final VotingUseCase votingUseCase;

    public VotingConsumer(VotingUseCase votingUseCase) {
        this.votingUseCase = votingUseCase;
    }

    @KafkaListener(topics = "voting-topic")
    public void processMessage(String kafkaMessage) {
        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        if ((boolean) map.get("isThumbUp")) {
            votingUseCase.thumbsUp((Long) map.get("id"));
        } else {
            votingUseCase.thumbsDown((Long) map.get("id"));
        }
    }
}
