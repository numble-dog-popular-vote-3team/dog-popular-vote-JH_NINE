package com.jh9.lobbysystem.dog.application.port.in;


import com.jh9.lobbysystem.dog.domain.Dog;

public interface VotingUseCase {

    void thumbsUp(Long votingId);

    void thumbsDown(Long votingId);
}
