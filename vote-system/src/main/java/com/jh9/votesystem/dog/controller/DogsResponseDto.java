package com.jh9.votesystem.dog.controller;

import com.jh9.votesystem.dog.domain.Dog;
import java.time.LocalDateTime;

public record DogsResponseDto(
    Long id,
    String name,
    String photoUrl,
    String simpleDescription,
    int thumbs,
    LocalDateTime createdAt
) {

    public static DogsResponseDto toDto(Dog dog) {
        return new DogsResponseDto(dog.getId(), dog.getName(), dog.getPhotoUrl(),
            dog.getSimpleDescription(),
            dog.getThumbs(), dog.getCreatedDate());
    }
}
