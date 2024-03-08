package br.dev.tobias.web.resource;

import br.dev.tobias.domain.command.CreatePlayerCommand;
import br.dev.tobias.domain.command.UpdatePlayerCommand;
import br.dev.tobias.domain.dto.InvalidCommandDTO;
import br.dev.tobias.domain.dto.PagePlayerDTO;
import br.dev.tobias.domain.dto.PlayerDTO;
import br.dev.tobias.domain.service.PlayerService;
import io.quarkus.panache.common.Page;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.net.URI;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/rest/v1/players")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class PlayerResource {
    @Inject
    PlayerService playerService;

    @POST
    @Path("/")
    @APIResponse(responseCode = "201", description = "CREATED", content = {
            @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = PlayerDTO.class))
    })
    @APIResponse(responseCode = "400", description = "Bad Request", content = {
            @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = InvalidCommandDTO.class))
    })
    public Response createPlayer(CreatePlayerCommand createPlayerCommand) {
        var player = playerService.createPlayer(createPlayerCommand);
        return Response.created(URI.create("/rest/v1/player/".concat(player.id().toString())))
                .entity(player)
                .build();
    }

    @PUT
    @Path("/{id}")
    @APIResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = PlayerDTO.class))
    })
    @APIResponse(responseCode = "400", description = "Bad Request", content = {
            @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = InvalidCommandDTO.class))
    })
    public Response updateTeam(Long id, UpdatePlayerCommand updatePlayerCommand) {
        var team = playerService.updatePlayer(id, updatePlayerCommand);
        return Response
                .ok()
                .entity(team)
                .build();
    }

    @DELETE
    @Path("/{id}")
    @APIResponse(responseCode = "204", description = "OK", content = {
            @Content(mediaType = APPLICATION_JSON)
    })
    @APIResponse(responseCode = "400", description = "Bad Request", content = {
            @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = InvalidCommandDTO.class))
    })
    public Response deleteTeam(Long id) {
        playerService.deletePlayer(id);
        return Response
                .noContent()
                .build();
    }

    @GET
    @Path("/{id}")
    @APIResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = PlayerDTO.class))
    })
    @APIResponse(responseCode = "404", description = "Bad Request", content = {
            @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = InvalidCommandDTO.class))
    })
    public Response readById(Long id) {
        var team = playerService.readById(id);
        return Response
                .ok()
                .entity(team)
                .build();
    }

    @GET
    @Path("/")
    @APIResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = PagePlayerDTO.class, type = SchemaType.ARRAY))
    })
    public Response readAll(@QueryParam("index") @DefaultValue("0") Integer index,
                            @QueryParam("size") @DefaultValue("10") Integer size) {
        var page = Page.of(index, size);
        var teams = playerService.readAllPaginated(page);
        return Response
                .ok()
                .entity(teams)
                .build();
    }
}
