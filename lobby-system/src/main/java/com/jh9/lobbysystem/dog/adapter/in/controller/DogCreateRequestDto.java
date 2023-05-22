package com.jh9.lobbysystem.dog.adapter.in.controller;

import com.jh9.lobbysystem.dog.domain.Dog;
import javax.validation.constraints.NotBlank;

record DogCreateRequestDto(
    @NotBlank String name,
    @NotBlank String photoUrl,
    @NotBlank String simpleDescription,
    @NotBlank String detailDescription
) {

    public Dog toEntity() {
        return Dog.createNew(name, photoUrl, simpleDescription, detailDescription);
    }
}
