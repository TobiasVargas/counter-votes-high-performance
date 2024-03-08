package br.dev.tobias.web.resource;

import br.dev.tobias.domain.command.CreateTeamCommand;
import br.dev.tobias.domain.command.UpdateTeamCommand;
import br.dev.tobias.domain.dto.InvalidCommandDTO;
import br.dev.tobias.domain.dto.PageTeamDTO;
import br.dev.tobias.domain.dto.TeamDTO;
import br.dev.tobias.domain.service.TeamService;
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
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.net.URI;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/rest/v1/teams")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class TeamResource {
    @Inject
    TeamService teamService;

    @POST
    @Path("/")
    @APIResponse(responseCode = "201", description = "CREATED", content = {
            @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = TeamDTO.class))
    })
    @APIResponse(responseCode = "400", description = "Bad Request", content = {
            @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = InvalidCommandDTO.class))
    })
    public Response createTeam(CreateTeamCommand createTeamCommand) {

        var team = teamService.createAndGetTeam(createTeamCommand);
        return Response
                .created(URI.create("/rest/v1/teams/".concat(team.id().toString())))
                .entity(team)
                .build();
    }

    @PUT
    @Path("/{id}")
    @APIResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = TeamDTO.class))
    })
    @APIResponse(responseCode = "400", description = "Bad Request", content = {
            @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = InvalidCommandDTO.class))
    })
    public Response updateTeam(Long id, UpdateTeamCommand updateTeamCommand) {
        var team = teamService.updateTeam(id, updateTeamCommand);
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
        teamService.deleteTeam(id);
        return Response
                .noContent()
                .build();
    }

    @GET
    @Path("/{id}")
    @APIResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = TeamDTO.class))
    })
    @APIResponse(responseCode = "404", description = "Bad Request", content = {
            @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = InvalidCommandDTO.class))
    })
    public Response readById(Long id) {
        var team = teamService.readById(id);
        return Response
                .ok()
                .entity(team)
                .build();
    }

    @GET
    @Path("/")
    @APIResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = PageTeamDTO.class))
    })
    public Response readAll(@QueryParam("index") @DefaultValue("0") Integer index,
                            @QueryParam("size") @DefaultValue("10") Integer size) {
        Page page = Page.of(index, size);
        var teams = teamService.readAllTeamsPaginated(page);
        return Response
                .ok()
                .entity(teams)
                .build();
    }
}
