package br.dev.tobias.domain.validator;

import br.dev.tobias.domain.dto.InvalidCommandDTO;
import br.dev.tobias.domain.dto.InvalidFieldDTO;
import br.dev.tobias.domain.exception.InvalidCommandException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Validator;

@ApplicationScoped
public class CommandValidator {
    @Inject
    Validator validator;
    public void validate(Object command) {
        var violations = validator.validate(command);
        if (violations.isEmpty()) {
            return;
        }
        var fields = violations.stream().map(violation -> new InvalidFieldDTO(violation.getPropertyPath().toString(), violation.getMessage()))
                .toList();
        throw new InvalidCommandException(new InvalidCommandDTO(fields));
    }
}
