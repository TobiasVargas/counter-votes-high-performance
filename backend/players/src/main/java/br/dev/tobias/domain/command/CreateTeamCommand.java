package br.dev.tobias.domain.command;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CreateTeamCommand(
        @NotEmpty(message = "Nome é obrigatório")
        @Size(max = 100, message = "Nome não pode exceder 100 caracteres")
        String name
) {
}
