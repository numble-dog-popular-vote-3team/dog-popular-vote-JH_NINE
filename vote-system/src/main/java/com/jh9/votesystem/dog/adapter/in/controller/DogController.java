package com.jh9.votesystem.dog.adapter.in.controller;

import com.jh9.votesystem.config.config.ZipkinLog;
import com.jh9.votesystem.dog.application.port.in.DogSearchCondition;
import com.jh9.votesystem.dog.application.port.in.DogUseCase;
import com.jh9.votesystem.dog.domain.Dog;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class DogController {

    private final DogUseCase dogUseCase;

    DogController(DogUseCase dogUseCase) {
        this.dogUseCase = dogUseCase;
    }

    @ZipkinLog("dog-backend.findDogs")
    @GetMapping("/dogs")
    public ResponseEntity<List<DogResponse>> findDogs(DogSearchCondition condition) {
        List<Long> dogIds = dogUseCase.findByCondition(condition);
        List<DogResponse> responseBody = dogIds.stream()
            .map(DogResponse::toDto)
            .toList();

        return ResponseEntity.status(HttpStatus.OK)
            .body(responseBody);
    }

    @ZipkinLog("dog-backend.findDog")
    @GetMapping("/dogs/{id}")
    public ResponseEntity<DogDetailResponseDto> findDog(
        @PathVariable Long id) {
        Dog dog = dogUseCase.findById(id);

        DogDetailResponseDto responseBody = DogDetailResponseDto.toDto(dog);

        return ResponseEntity.status(HttpStatus.OK)
            .body(responseBody);
    }
}
