package br.dev.tobias.domain.mapper;

import br.dev.tobias.domain.command.CreateTeamCommand;
import br.dev.tobias.domain.command.UpdateTeamCommand;
import br.dev.tobias.domain.dto.TeamDTO;
import br.dev.tobias.domain.entity.Team;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TeamMapper {
    public Team map(CreateTeamCommand createTeamCommand) {
        return new Team(createTeamCommand.name());
    }

    public TeamDTO map(Team team) {
        return new TeamDTO(team.getId(), team.getName());
    }

    public void map(Team team, UpdateTeamCommand updateTeamCommand) {
        team.setName(updateTeamCommand.name());
    }
}
