package br.dev.tobias.message.consumer;

import br.dev.tobias.domain.event.VoteEvent;
import br.dev.tobias.domain.exception.CommunicationWithPlayerServiceException;
import br.dev.tobias.domain.service.CounterService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class VoteConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(VoteConsumer.class);
    @Inject
    CounterService counterService;

    @Incoming("votes-computing")
    @Transactional
    public void receive(VoteEvent voteEvent) throws CommunicationWithPlayerServiceException {
        LOGGER.info("MENSAGEM RECEBIDA {}", voteEvent.playerId());
        counterService.computeVote(voteEvent);
    }
}
