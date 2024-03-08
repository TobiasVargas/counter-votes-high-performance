package br.dev.tobias.domain.mapper;

import br.dev.tobias.domain.dto.PlayerDTO;
import br.dev.tobias.domain.entity.Player;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlayerMapper {
    public Player map(PlayerDTO playerDTO) {
        return new Player(playerDTO.getId(), playerDTO.getName(), playerDTO.getShirtNumber(), playerDTO.getTeamName());
    }
}
