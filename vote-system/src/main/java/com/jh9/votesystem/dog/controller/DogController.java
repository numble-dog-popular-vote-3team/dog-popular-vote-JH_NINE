package com.jh9.votesystem.dog.controller;

import com.jh9.votesystem.dog.application.DogService;
import com.jh9.votesystem.dog.domain.Dog;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
class DogController {

    private final DogService dogService;

    DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @GetMapping("/dogs")
    public ResponseEntity<List<DogsResponseDto>> showCandidates(@PageableDefault(size = 8, sort = "createdDate") Pageable pageable) {
        List<Dog> dogs = dogService.showCandidates(pageable);
        
        List<DogsResponseDto> responseBody = dogs.stream()
            .map(DogsResponseDto::toDto)
            .toList();

        return ResponseEntity.status(HttpStatus.OK)
            .body(responseBody);
    }

    @GetMapping("/dogs/{id}")
    public ResponseEntity<DogDetailResponseDto> showCandidate(@PathVariable Long id) {
        Dog dog = dogService.showCandidate(id);

        DogDetailResponseDto responseBody = DogDetailResponseDto.toDto(dog);

        return ResponseEntity.status(HttpStatus.OK)
            .body(responseBody);
    }

    @PostMapping("/dogs")
    public ResponseEntity<Void> createCandidate(DogCreateRequestDto requestDto) {
        dogService.createCandidate(requestDto.toEntity());

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(null);
    }

    @PostMapping("/dogs/{id}/thumbsUp")
    public ResponseEntity<DogDetailResponseDto> thumbsUp(
        @RequestHeader("X-Forwarded-For") String ipAddress,
        @PathVariable Long id) {
        Dog updatedDog = dogService.thumbsUp(id);

        DogDetailResponseDto responseBody = DogDetailResponseDto.toDto(updatedDog);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PostMapping("/dogs/{id}/thumbsDown")
    public ResponseEntity<DogDetailResponseDto> thumbsDown(
        @RequestHeader("X-Forwarded-For") String ipAddress,
        @PathVariable Long id) {
        Dog updatedDog = dogService.thumbsDown(id);

        DogDetailResponseDto responseBody = DogDetailResponseDto.toDto(updatedDog);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
