package com.jh9.lobbysystem.dog.adapter.in.controller;

import com.jh9.lobbysystem.dog.domain.Dog;
import java.io.Serializable;
import java.time.LocalDateTime;

record DogsListResponse(
    Long id,
    String name,
    String photoUrl,
    String simpleDescription,
    int thumbs,
    LocalDateTime createDate
) implements Serializable {

    public static DogsListResponse of(Dog dog) {
        return new DogsListResponse(dog.id(), dog.name(), dog.photoUrl(), dog.simpleDescription(),
            dog.thumbs(), dog.createDate());
    }
}
