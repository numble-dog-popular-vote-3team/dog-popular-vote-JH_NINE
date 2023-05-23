package com.jh9.lobbysystem.dog.adapter.in.controller;

import com.jh9.lobbysystem.dog.domain.Dog;
import java.io.Serializable;
import java.time.LocalDateTime;

record DogDetailResponse(
    Long id,
    String name,
    String photoUrl,
    String simpleDescription,
    String detailDescription,
    int thumbs,
    LocalDateTime createDate
) implements Serializable {

    public static DogDetailResponse of(Dog dog) {
        return new DogDetailResponse(dog.id(), dog.name(), dog.photoUrl(),
            dog.simpleDescription(), dog.detailDescription(), dog.thumbs(), dog.createDate());
    }
}
