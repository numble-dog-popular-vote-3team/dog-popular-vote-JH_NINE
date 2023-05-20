package com.jh9.votesystem.dog.application.port.in;

import com.jh9.votesystem.dog.domain.Dog;

public interface VotingUseCase {

    Dog thumbsUp(Long votingId);

    Dog thumbsDown(Long votingId);
}
