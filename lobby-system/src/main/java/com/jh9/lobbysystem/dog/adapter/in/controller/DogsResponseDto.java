package com.jh9.lobbysystem.dog.adapter.in.controller;

import com.jh9.lobbysystem.dog.domain.Dog;
import java.time.LocalDateTime;

record DogsResponseDto(
    Long id,
    String name,
    String photoUrl,
    String simpleDescription,
    int thumbs,
    LocalDateTime createDate
) {

    public static DogsResponseDto toDto(Dog dog) {
        return new DogsResponseDto(dog.getId(), dog.getName(), dog.getPhotoUrl(),
            dog.getSimpleDescription(),
            dog.getThumbs(), dog.getCreatedDate());
    }
}
