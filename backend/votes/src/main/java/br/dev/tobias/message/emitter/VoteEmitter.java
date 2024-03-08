package br.dev.tobias.message.emitter;

import br.dev.tobias.domain.event.VoteEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.OnOverflow;

@ApplicationScoped
public class VoteEmitter {
    @Inject
    @Channel("votes-computing")
    @OnOverflow(value = OnOverflow.Strategy.UNBOUNDED_BUFFER)
    Emitter<VoteEvent> voteEventEmitter;

    public void send(VoteEvent voteEvent) {
        voteEventEmitter.send(voteEvent);
    }
}
