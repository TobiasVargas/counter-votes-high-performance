package br.dev.tobias.infra.repository;

import br.dev.tobias.domain.entity.Player;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlayerRepository implements PanacheRepository<Player> {
    public boolean existsPlayerByTeamId(Long teamId) {
        var query = "team.id = :team_id";
        return find(query, Parameters.with("team_id", teamId)).singleResultOptional().isPresent();
    }
}
