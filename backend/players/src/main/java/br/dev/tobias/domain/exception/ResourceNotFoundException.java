package br.dev.tobias.domain.exception;

import br.dev.tobias.domain.dto.InvalidCommandDTO;

import java.util.List;

public class ResourceNotFoundException extends RuntimeException {
    private final InvalidCommandDTO invalidCommandDTO;
    public ResourceNotFoundException(String message) {
        super(message);
        this.invalidCommandDTO = new InvalidCommandDTO(message, List.of());
    }

    public InvalidCommandDTO getInvalidCommandDTO() {
        return invalidCommandDTO;
    }
}
