package com.jh9.votesystem.dog.adapter.in.controller;

import com.jh9.votesystem.dog.domain.Dog;
import java.time.LocalDateTime;

record DogResponse(
    Long id
) {

    public static DogResponse toDto(Long id) {
        return new DogResponse(id);
    }
}
