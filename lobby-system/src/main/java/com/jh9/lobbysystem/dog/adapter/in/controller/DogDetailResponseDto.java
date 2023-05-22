package com.jh9.lobbysystem.dog.adapter.in.controller;

import com.jh9.lobbysystem.dog.domain.Dog;
import java.io.Serializable;

record DogDetailResponseDto(
    Long id,
    String name,
    String photoUrl,
    String simpleDescription,
    String detailDescription,
    int thumbs
) implements Serializable {

    public static DogDetailResponseDto toDto(Dog dog) {
        return new DogDetailResponseDto(dog.getId(), dog.getName(), dog.getPhotoUrl(), dog.getSimpleDescription(),
            dog.getDetailDescription(), dog.getThumbs());
    }
}
