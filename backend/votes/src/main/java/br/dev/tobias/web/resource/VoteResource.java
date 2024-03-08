package br.dev.tobias.web.resource;

import br.dev.tobias.domain.dto.VoteDTO;
import br.dev.tobias.domain.service.VoteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/rest/v1/votes")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class VoteResource {
    @Inject
    VoteService voteService;

    @Path("/")
    @POST
    @APIResponse(responseCode = "204", description = "OK", content = {
            @Content(mediaType = APPLICATION_JSON)
    })
    public Response sendVote(VoteDTO voteDTO) {
        voteService.sendVote(voteDTO);
        return Response.noContent().build();
    }
}
