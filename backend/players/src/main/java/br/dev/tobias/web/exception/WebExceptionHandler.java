package br.dev.tobias.web.exception;

import br.dev.tobias.domain.dto.InvalidCommandDTO;
import br.dev.tobias.domain.exception.InvalidCommandException;
import br.dev.tobias.domain.exception.ResourceNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static jakarta.ws.rs.core.Response.Status.BAD_REQUEST;
import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

@Provider
public class WebExceptionHandler implements ExceptionMapper<Throwable> {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebExceptionHandler.class);
    @Override
    public Response toResponse(Throwable exception) {
        if (exception instanceof InvalidCommandException e) {
            return Response
                    .status(BAD_REQUEST)
                    .entity(e.getInvalidCommandDTO())
                    .build();
        }
        if (exception instanceof ResourceNotFoundException e) {
            return Response
                    .status(NOT_FOUND)
                    .entity(e.getInvalidCommandDTO())
                    .build();
        }
        LOGGER.error("Erro não mapeado", exception);
        var invalidCommand = new InvalidCommandDTO("Encontramos um erro interno ao processar sua solicitação. Estamos trabalhando para resolver.");
        return Response.serverError().entity(invalidCommand).build();
    }
}
