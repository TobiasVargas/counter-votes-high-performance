package br.dev.tobias.domain.command;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdatePlayerCommand(
        @NotEmpty(message = "Nome não pode estar vazio")
        String name,
        @NotNull(message = "Camisa não pode estar vazia")
        int shirtNumber,
        @NotNull(message = "Time precisa ser escolhido")
        Long teamId
) {

}
