package com.jh9.votesystem.dog.controller;

import com.jh9.votesystem.dog.domain.Dog;
import javax.validation.constraints.NotBlank;

public record DogCreateRequestDto(
    @NotBlank String name,
    @NotBlank String photoUrl,
    @NotBlank String simpleDescription,
    @NotBlank String detailDescription
) {

    public Dog toEntity() {
        return Dog.createNewCandidate(name, photoUrl, simpleDescription, detailDescription);
    }
}
