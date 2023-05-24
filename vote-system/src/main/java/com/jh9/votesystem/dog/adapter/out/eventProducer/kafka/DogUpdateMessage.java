package com.jh9.votesystem.dog.adapter.out.eventProducer.kafka;

import java.io.Serializable;

record DogUpdateMessage(
    String serverName,
    Long id
) implements Serializable {

}
