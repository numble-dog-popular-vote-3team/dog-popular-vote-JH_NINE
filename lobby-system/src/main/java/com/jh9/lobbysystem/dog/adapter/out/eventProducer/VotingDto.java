package com.jh9.lobbysystem.dog.adapter.out.eventProducer;

import java.io.Serializable;

public record VotingDto(
    Long id,
    boolean isThumbUp
) implements Serializable {

}
