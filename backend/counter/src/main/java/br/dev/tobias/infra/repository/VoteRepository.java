package br.dev.tobias.infra.repository;

import br.dev.tobias.domain.dto.TotalVotesDTO;
import br.dev.tobias.domain.entity.Vote;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class VoteRepository implements PanacheRepository<Vote> {
    @Inject
    EntityManager entityManager;

    public boolean existsVoteWithUuid(UUID uuid) {
        return find("uuid = :uuid", Parameters.with("uuid", uuid))
                .firstResultOptional()
                .isPresent();
    }

    public List<TotalVotesDTO> findTotalVotes() {
        var query = """
                select new br.dev.tobias.domain.dto.TotalVotesDTO(
                  v.player.id,
                  v.player.name,
                  v.player.shirtNumber,
                  v.player.teamName,
                  count(*)
                )
                from Vote v
                join Player p on p.id = v.player.id
                group by v.player.id, v.player.name, v.player.shirtNumber, v.player.teamName
                order by count(*) desc
                """;
        return entityManager.createQuery(query, TotalVotesDTO.class).getResultList();
    }
}
