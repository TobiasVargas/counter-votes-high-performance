package br.dev.tobias.domain.dto;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

import java.util.List;

public class PagePlayerDTO extends PageDTO<PlayerDTO> {
    public PagePlayerDTO(PanacheQuery<?> query, List<PlayerDTO> data) {
        super(query, data);
    }
}
