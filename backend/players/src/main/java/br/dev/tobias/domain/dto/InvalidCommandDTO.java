package br.dev.tobias.domain.dto;

import java.util.List;

public class InvalidCommandDTO {
    private final String message;
    private List<InvalidFieldDTO> fields;

    public InvalidCommandDTO(String message, List<InvalidFieldDTO> fields) {
        this.message = message;
        this.fields = fields;
    }

    public InvalidCommandDTO(List<InvalidFieldDTO> fields) {
        this.message = "Comando inválido";
        this.fields = fields;
    }

    public InvalidCommandDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public List<InvalidFieldDTO> getFields() {
        return fields;
    }
}
