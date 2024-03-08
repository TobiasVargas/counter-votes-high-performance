package br.dev.tobias.domain.dto;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

import java.util.List;

public class PageTeamDTO extends PageDTO<TeamDTO> {
    public PageTeamDTO(PanacheQuery<?> query, List<TeamDTO> data) {
        super(query, data);
    }
}
