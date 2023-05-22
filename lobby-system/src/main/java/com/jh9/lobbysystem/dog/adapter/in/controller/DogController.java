package com.jh9.lobbysystem.dog.adapter.in.controller;

import com.jh9.lobbysystem.dog.application.port.in.DogSearchCondition;
import com.jh9.lobbysystem.dog.application.port.in.DogUseCase;
import com.jh9.lobbysystem.dog.application.port.in.VotingUseCase;
import com.jh9.lobbysystem.dog.domain.Dog;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class DogController {

    private final DogUseCase dogUseCase;
    private final VotingUseCase votingUseCase;

    DogController(DogUseCase dogUseCase, VotingUseCase votingUseCase) {
        this.dogUseCase = dogUseCase;
        this.votingUseCase = votingUseCase;
    }

    @GetMapping("/dogs")
    public ResponseEntity<List<DogsResponseDto>> showCandidates(
        @CookieValue(value = "dog-vote", required = false) String userCookie,
        Long lastId,
        @RequestParam(defaultValue = "8") int pageSize) {

        DogSearchCondition condition = new DogSearchCondition(userCookie,
            pageSize, lastId);
        List<Dog> dogs = dogUseCase.showCandidates(condition);
        
        List<DogsResponseDto> responseBody = dogs.stream()
            .map(DogsResponseDto::toDto)
            .toList();

        return ResponseEntity.status(HttpStatus.OK)
            .body(responseBody);
    }

    @GetMapping("/dogs/{id}")
    public ResponseEntity<DogDetailResponseDto> showCandidate(
//        @RequestHeader("X-Forwarded-For") String ipAddress,
        @PathVariable Long id) {
        Dog dog = dogUseCase.showCandidate(id);

        DogDetailResponseDto responseBody = DogDetailResponseDto.toDto(dog);

        return ResponseEntity.status(HttpStatus.OK)
            .body(responseBody);
    }

    @PostMapping("/dogs")
    public ResponseEntity<Void> createCandidate(
        @RequestHeader("X-Forwarded-For") String ipAddress,
        @Valid DogCreateRequestDto requestDto) {
        dogUseCase.createCandidate(requestDto.toEntity());

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(null);
    }

    @PostMapping("/dogs/{id}/thumbsUp")
    public ResponseEntity<Void> thumbsUp(
//        @RequestHeader("X-Forwarded-For") String ipAddress,
        @PathVariable Long id) {
        votingUseCase.thumbsUp(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PostMapping("/dogs/{id}/thumbsDown")
    public ResponseEntity<Void> thumbsDown(
//        @RequestHeader("X-Forwarded-For") String ipAddress,
        @PathVariable Long id) {
        votingUseCase.thumbsDown(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
