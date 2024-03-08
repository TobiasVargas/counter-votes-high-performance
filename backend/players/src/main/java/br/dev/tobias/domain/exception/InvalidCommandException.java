package br.dev.tobias.domain.exception;

import br.dev.tobias.domain.dto.InvalidCommandDTO;

public class InvalidCommandException extends RuntimeException {
    private final InvalidCommandDTO invalidCommandDTO;

    public InvalidCommandException(InvalidCommandDTO invalidCommandDTO) {
        super(invalidCommandDTO.getMessage());
        this.invalidCommandDTO = invalidCommandDTO;
    }

    public InvalidCommandDTO getInvalidCommandDTO() {
        return invalidCommandDTO;
    }
}
