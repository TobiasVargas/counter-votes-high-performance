package br.dev.tobias.domain.service;

import br.dev.tobias.domain.dto.VoteDTO;
import br.dev.tobias.domain.event.VoteEvent;
import br.dev.tobias.message.emitter.VoteEmitter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.UUID;

@ApplicationScoped
public class VoteService {
    @Inject
    VoteEmitter voteEmitter;

    public void sendVote(VoteDTO voteDTO) {
        var event = new VoteEvent(voteDTO.playerId(), UUID.randomUUID());
        voteEmitter.send(event);
    }
}
