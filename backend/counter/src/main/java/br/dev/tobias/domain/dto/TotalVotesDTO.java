package br.dev.tobias.domain.dto;

public class TotalVotesDTO extends PlayerDTO {
    private final Long count;

    public TotalVotesDTO(Long id, String name, int shirtNumber, String teamName, Long count) {
        super(id, name, shirtNumber, teamName);
        this.count = count;
    }

    public Long getCount() {
        return count;
    }
}
