package com.jh9.votesystem.dog.controller;

import com.jh9.votesystem.dog.domain.Dog;
import java.io.Serializable;
import java.time.LocalDateTime;

public record DogDetailResponseDto (
    Long id,
    String name,
    String photoUrl,
    String simpleDescription,
    String detailDescription,
    int thumbs,
    LocalDateTime createdAt
) implements Serializable {

    public static DogDetailResponseDto toDto(Dog dog) {
        return new DogDetailResponseDto(dog.getId(), dog.getName(), dog.getPhotoUrl(),
            dog.getSimpleDescription(),
            dog.getDetailDescription(), dog.getThumbs(), dog.getCreatedDate());
    }
}
