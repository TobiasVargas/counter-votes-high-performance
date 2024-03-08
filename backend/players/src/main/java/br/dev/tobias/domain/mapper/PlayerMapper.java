package br.dev.tobias.domain.mapper;

import br.dev.tobias.domain.command.CreatePlayerCommand;
import br.dev.tobias.domain.command.UpdatePlayerCommand;
import br.dev.tobias.domain.dto.PlayerDTO;
import br.dev.tobias.domain.entity.Player;
import br.dev.tobias.domain.entity.Team;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlayerMapper {
    public Player map(CreatePlayerCommand createPlayerCommand, Team team) {
        return new Player(createPlayerCommand.name(), createPlayerCommand.shirtNumber(), team);
    }

    public PlayerDTO map(Player player) {
        return new PlayerDTO(player.getId(), player.getName(), player.getShirtNumber(), player.getTeam().getName());
    }

    public void map(Player player, UpdatePlayerCommand updatePlayerCommand, Team team) {
        player.setName(updatePlayerCommand.name());
        player.setShirtNumber(updatePlayerCommand.shirtNumber());
        player.setTeam(team);
    }
}
