package com.jh9.votesystem.dog.adapter.in.controller;

record DogResponse(
    Long id
) {

    public static DogResponse toDto(Long id) {
        return new DogResponse(id);
    }
}
