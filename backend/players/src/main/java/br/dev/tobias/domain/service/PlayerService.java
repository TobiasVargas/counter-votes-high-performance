package br.dev.tobias.domain.service;

import br.dev.tobias.domain.command.CreatePlayerCommand;
import br.dev.tobias.domain.command.UpdatePlayerCommand;
import br.dev.tobias.domain.dto.InvalidCommandDTO;
import br.dev.tobias.domain.dto.InvalidFieldDTO;
import br.dev.tobias.domain.dto.PagePlayerDTO;
import br.dev.tobias.domain.dto.PlayerDTO;
import br.dev.tobias.domain.entity.Team;
import br.dev.tobias.domain.exception.InvalidCommandException;
import br.dev.tobias.domain.exception.ResourceNotFoundException;
import br.dev.tobias.domain.mapper.PlayerMapper;
import br.dev.tobias.domain.validator.CommandValidator;
import br.dev.tobias.infra.repository.PlayerRepository;
import br.dev.tobias.infra.repository.TeamRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class PlayerService {
    @Inject
    PlayerRepository playerRepository;
    @Inject
    CommandValidator validator;
    @Inject
    TeamRepository teamRepository;
    @Inject
    PlayerMapper playerMapper;

    @Transactional
    public PlayerDTO createPlayer(CreatePlayerCommand createPlayerCommand) {
        validator.validate(createPlayerCommand);
        var team = getTeamOrThrowsInvalidCommand(createPlayerCommand.teamId());
        var player = playerMapper.map(createPlayerCommand, team);
        playerRepository.persist(player);
        return playerMapper.map(player);
    }

    @Transactional
    public PlayerDTO updatePlayer(Long id, UpdatePlayerCommand updatePlayerCommand) {
        validator.validate(updatePlayerCommand);
        var player = playerRepository.findByIdOptional(id).orElseThrow(() -> new ResourceNotFoundException("Jogador não encontrado"));
        var team = getTeamOrThrowsInvalidCommand(updatePlayerCommand.teamId());
        playerMapper.map(player, updatePlayerCommand, team);
        playerRepository.persist(player);
        return playerMapper.map(player);
    }

    @Transactional
    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }

    public PagePlayerDTO readAllPaginated(Page page) {
        var query = playerRepository.findAll().page(page);
        var players = query.list().stream().map(player -> playerMapper.map(player)).toList();
        return new PagePlayerDTO(query, players);
    }

    public PlayerDTO readById(Long id) {
        var player = playerRepository.findByIdOptional(id).orElseThrow(() -> new ResourceNotFoundException("Jogador não encontrado."));
        return playerMapper.map(player);
    }

    private Team getTeamOrThrowsInvalidCommand(Long teamId) {
        var team = teamRepository.findByIdOptional(teamId);
        if (team.isPresent()) {
            return team.get();
        }
        var invalidField = new InvalidFieldDTO("teamId", "Não foi encontrado time com este identificador");
        throw new InvalidCommandException(new InvalidCommandDTO(List.of(invalidField)));
    }

    public boolean existsPlayerByTeamId(Long teamId) {
        return playerRepository.existsPlayerByTeamId(teamId);
    }
}
