package com.jh9.lobbysystem.dog.adapter.in.controller;

import com.jh9.lobbysystem.dog.application.port.in.DogSearchCondition;
import com.jh9.lobbysystem.dog.application.port.in.DogUseCase;
import com.jh9.lobbysystem.dog.application.port.in.VotingUseCase;
import com.jh9.lobbysystem.dog.domain.Dog;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
class DogController {

    private final DogUseCase dogUseCase;
    private final VotingUseCase votingUseCase;

    DogController(DogUseCase dogUseCase, VotingUseCase votingUseCase) {
        this.dogUseCase = dogUseCase;
        this.votingUseCase = votingUseCase;
    }

    @GetMapping("/dogs")
    public ResponseEntity<Flux<DogsResponseDto>> showCandidates(
        @CookieValue(value = "dog-vote", required = false) String userCookie,
        DogSearchCondition condition) {
        condition.setUserCookie(userCookie);

        dogUseCase.showCandidates(condition);

        return ResponseEntity.status(HttpStatus.OK)
            .body(null);
    }

    @GetMapping("/dogs/{id}")
    public ResponseEntity<Mono<DogDetailResponseDto>> showCandidate(
//        @RequestHeader("X-Forwarded-For") String ipAddress,
        @PathVariable Long id) {


        return ResponseEntity.status(HttpStatus.OK)
            .body(null);
    }

    @PostMapping("/dogs")
    public ResponseEntity<Void> createCandidate(
        @RequestHeader("X-Forwarded-For") String ipAddress,
        @Valid DogCreateRequestDto requestDto) {

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
