package com.jh9.lobbysystem.dog.application;

import com.jh9.lobbysystem.dog.application.port.in.VotingUseCase;
import com.jh9.lobbysystem.dog.application.port.out.eventProducer.SendVotingEventPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
public class VotingService implements VotingUseCase {

    private final SendVotingEventPort sendVotingEventPort;

    public VotingService(SendVotingEventPort sendVotingEventPort) {
        this.sendVotingEventPort = sendVotingEventPort;
    }

    @Transactional
    public Mono<Void> thumbsUp(Long votingId) {
        return sendVotingEventPort.sendTo("dog-voting", votingId, true);
    }

    @Transactional
    public Mono<Void> thumbsDown(Long votingId) {
        return sendVotingEventPort.sendTo("dog-voting", votingId, false);
    }
}
