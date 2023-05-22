package com.jh9.lobbysystem.dog.application;

import com.jh9.lobbysystem.dog.application.port.in.VotingUseCase;
import com.jh9.lobbysystem.dog.application.port.out.eventProducer.SendVotingEventPort;
import com.jh9.lobbysystem.dog.domain.Dog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VotingService implements VotingUseCase {

    private final SendVotingEventPort sendVotingEventPort;

    public VotingService(SendVotingEventPort sendVotingEventPort) {
        this.sendVotingEventPort = sendVotingEventPort;
    }

    @Transactional
    public void thumbsUp(Long votingId) {
        sendVotingEventPort.sendTo("dog-voting", votingId, true);
    }

    @Transactional
    public void thumbsDown(Long votingId) {
        sendVotingEventPort.sendTo("dog-voting", votingId, false);
    }
}
