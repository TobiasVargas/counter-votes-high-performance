package br.dev.tobias.domain.service;

import br.dev.tobias.domain.dto.PlayerDTO;
import br.dev.tobias.domain.dto.TotalVotesDTO;
import br.dev.tobias.domain.entity.Player;
import br.dev.tobias.domain.entity.Vote;
import br.dev.tobias.domain.event.VoteEvent;
import br.dev.tobias.domain.exception.CommunicationWithPlayerServiceException;
import br.dev.tobias.domain.exception.PlayerNotFoundException;
import br.dev.tobias.domain.mapper.PlayerMapper;
import br.dev.tobias.infra.client.PlayerClient;
import br.dev.tobias.infra.repository.PlayerRepository;
import br.dev.tobias.infra.repository.VoteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.ClientWebApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.jboss.resteasy.reactive.RestResponse.StatusCode.NOT_FOUND;

@ApplicationScoped
public class CounterService {
    @Inject
    @RestClient
    PlayerClient playerClient;
    @Inject
    PlayerRepository playerRepository;
    @Inject
    PlayerMapper playerMapper;
    @Inject
    VoteRepository voteRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(CounterService.class);

    @Transactional
    public void computeVote(VoteEvent voteEvent) throws CommunicationWithPlayerServiceException {
        Player player;
        try {
            player = getPlayerOrSaveIfNotExists(voteEvent.playerId());
        } catch (PlayerNotFoundException e) {
            LOGGER.info("JOGADOR NÃO ENCONTRADO --- IGNORANDO");
            return;
        } catch (CommunicationWithPlayerServiceException e) {
            LOGGER.error("NÃO FOI POSSÍVEL SE COMUNICAR COM O SERVIÇO players-service-api --- REPROCESSANDO", e);
            throw e;
        }

        if (voteRepository.existsVoteWithUuid(voteEvent.uuid())) {
            LOGGER.info("VOTO JÁ COMPUTADO --- IGNORANDO");
            return;
        }
        var vote = new Vote(voteEvent.uuid(), player);
        voteRepository.persist(vote);
    }

    private PlayerDTO getPlayerInPlayersService(Long playerId) throws PlayerNotFoundException, CommunicationWithPlayerServiceException {
        try {
            return playerClient.getById(playerId);
        } catch (ClientWebApplicationException e) {
            if (e.getResponse().getStatus() == NOT_FOUND) {
                throw new PlayerNotFoundException("Jogador ".concat(playerId.toString()).concat(" não existe"));
            }
            throw new CommunicationWithPlayerServiceException("Não foi possível se comunicar com o serviço de jogadores para consultar o jogador ".concat(playerId.toString()));
        }
    }

    private Player getPlayerOrSaveIfNotExists(Long playerId) throws PlayerNotFoundException, CommunicationWithPlayerServiceException {
        var player = playerRepository.findByIdOptional(playerId);
        if (player.isPresent()) {
            return player.get();
        }
        var playerDTO = getPlayerInPlayersService(playerId);
        var playerEntity = playerMapper.map(playerDTO);
        playerRepository.persist(playerEntity);
        return playerEntity;
    }

    public List<TotalVotesDTO> findTotalVotes() {
        return voteRepository.findTotalVotes();
    }
}
