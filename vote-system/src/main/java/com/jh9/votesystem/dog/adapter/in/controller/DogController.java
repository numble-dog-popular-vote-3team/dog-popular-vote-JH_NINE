package com.jh9.votesystem.dog.adapter.in.controller;

import com.jh9.votesystem.dog.application.port.in.DogUseCase;
import com.jh9.votesystem.dog.application.port.in.VotingUseCase;
import com.jh9.votesystem.dog.domain.Dog;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class DogController {

    private static final Logger log = LoggerFactory.getLogger(DogController.class);
    private final DogUseCase dogUseCase;
    private final VotingUseCase votingUseCase;

    DogController(DogUseCase dogUseCase, VotingUseCase votingUseCase) {
        this.dogUseCase = dogUseCase;
        this.votingUseCase = votingUseCase;
    }

    @GetMapping("/dogs")
    public ResponseEntity<List<DogsResponseDto>> showCandidates(
        Long lastId,
        @RequestParam(defaultValue = "8") int pageSize) {
        List<Dog> dogs = dogUseCase.showCandidates(lastId, pageSize);
        
        List<DogsResponseDto> responseBody = dogs.stream()
            .map(DogsResponseDto::toDto)
            .toList();

        return ResponseEntity.status(HttpStatus.OK)
            .body(responseBody);
    }

    @GetMapping("/dogs/{id}")
    public ResponseEntity<DogDetailResponseDto> showCandidate(@PathVariable Long id) {
        Dog dog = dogUseCase.showCandidate(id);

        DogDetailResponseDto responseBody = DogDetailResponseDto.toDto(dog);

        return ResponseEntity.status(HttpStatus.OK)
            .body(responseBody);
    }

    @PostMapping("/dogs")
    public ResponseEntity<Void> createCandidate(@Valid DogCreateRequestDto requestDto) {
        dogUseCase.createCandidate(requestDto.toEntity());

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(null);
    }

    @PostMapping("/dogs/{id}/thumbsUp")
    public ResponseEntity<DogDetailResponseDto> thumbsUp(
        @RequestHeader("X-Forwarded-For") String ipAddress,
        @PathVariable Long id) {
        Dog updatedDog = votingUseCase.thumbsUp(id);

        DogDetailResponseDto responseBody = DogDetailResponseDto.toDto(updatedDog);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PostMapping("/dogs/{id}/thumbsDown")
    public ResponseEntity<DogDetailResponseDto> thumbsDown(
        @RequestHeader("X-Forwarded-For") String ipAddress,
        @PathVariable Long id) {
        Dog updatedDog = votingUseCase.thumbsDown(id);

        DogDetailResponseDto responseBody = DogDetailResponseDto.toDto(updatedDog);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
