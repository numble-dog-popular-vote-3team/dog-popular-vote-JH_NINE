package com.jh9.votesystem.dog.adapter.out.eventProducer.kafka;

import com.jh9.votesystem.dog.application.port.out.eventProducer.DogChangeEventPort;
import com.jh9.votesystem.utils.Adapter;
import org.springframework.kafka.core.KafkaTemplate;

@Adapter
public class DogChangeProducer implements DogChangeEventPort {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public DogChangeProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendTo(String kafkaTopic, Long id) {
        kafkaTemplate.send(kafkaTopic, id.toString());
    }
}
