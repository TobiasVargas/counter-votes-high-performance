package br.dev.tobias.domain.service;

import br.dev.tobias.domain.command.CreateTeamCommand;
import br.dev.tobias.domain.command.UpdateTeamCommand;
import br.dev.tobias.domain.dto.InvalidCommandDTO;
import br.dev.tobias.domain.dto.PageTeamDTO;
import br.dev.tobias.domain.dto.TeamDTO;
import br.dev.tobias.domain.exception.InvalidCommandException;
import br.dev.tobias.domain.exception.ResourceNotFoundException;
import br.dev.tobias.domain.mapper.TeamMapper;
import br.dev.tobias.domain.validator.CommandValidator;
import br.dev.tobias.infra.repository.TeamRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TeamService {
    @Inject
    TeamRepository teamRepository;
    @Inject
    TeamMapper teamMapper;
    @Inject
    CommandValidator commandValidator;
    @Inject
    PlayerService playerService;

    @Transactional
    public TeamDTO createAndGetTeam(CreateTeamCommand createTeamCommand) {
        commandValidator.validate(createTeamCommand);
        var team = teamMapper.map(createTeamCommand);
        teamRepository.persist(team);
        return teamMapper.map(team);
    }

    @Transactional
    public TeamDTO updateTeam(Long id, UpdateTeamCommand updateTeamCommand) {
        commandValidator.validate(updateTeamCommand);
        var team = teamRepository.findById(id);
        teamMapper.map(team, updateTeamCommand);
        teamRepository.persist(team);
        return teamMapper.map(team);
    }

    @Transactional
    public void deleteTeam(Long id) {
        validateIfNotUsingInPlayer(id);
        teamRepository.deleteById(id);
    }

    private void validateIfNotUsingInPlayer(Long id) {
        if (playerService.existsPlayerByTeamId(id)) {
            throw new InvalidCommandException(new InvalidCommandDTO("Há jogadores relacionados com este time."));
        }
    }

    public PageTeamDTO readAllTeamsPaginated(Page page) {
        var query = teamRepository.findAll().page(page);
        var teams = query.list().stream().map(team -> teamMapper.map(team)).toList();
        return new PageTeamDTO(query, teams);
    }

    public TeamDTO readById(Long id) {
        var team = teamRepository.findByIdOptional(id).orElseThrow(() -> new ResourceNotFoundException("Time não encontrado"));
        return teamMapper.map(team);
    }

}
