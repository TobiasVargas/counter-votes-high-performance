package br.dev.tobias.domain.event;

import java.util.UUID;

public record VoteEvent (Long playerId, UUID uuid) {

}
