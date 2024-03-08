package br.dev.tobias.infra.client;

import br.dev.tobias.domain.dto.PlayerDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/rest/v1/players")
@RegisterRestClient(configKey = "clients.players")
@Produces(APPLICATION_JSON)
public interface PlayerClient {
    @GET
    @Path("/{id}")
    PlayerDTO getById(Long id);
}
