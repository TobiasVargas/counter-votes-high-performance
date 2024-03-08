package br.dev.tobias.web.resource;

import br.dev.tobias.domain.dto.TotalVotesDTO;
import br.dev.tobias.domain.service.CounterService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/rest/v1/counter")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class CounterResource {
    @Inject
    CounterService counterService;

    @GET
    @Path("/")
    @APIResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = TotalVotesDTO.class, type = SchemaType.ARRAY))
    })
    public Response countVotes() {
        var totalVotes = counterService.findTotalVotes();
        return Response.ok().entity(totalVotes).build();
    }
}
