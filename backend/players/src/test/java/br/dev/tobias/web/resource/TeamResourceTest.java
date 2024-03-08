package br.dev.tobias.web.resource;

import br.dev.tobias.domain.command.CreateTeamCommand;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static jakarta.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.jboss.resteasy.reactive.RestResponse.StatusCode.BAD_REQUEST;
import static org.jboss.resteasy.reactive.RestResponse.StatusCode.CREATED;

@QuarkusTest
class TeamResourceTest {
    private static final CreateTeamCommand VALID_CREATE_TEAM_MOCK = new CreateTeamCommand("Internacional");
    private static final CreateTeamCommand INVALID_CREATE_TEAM_MOCK = new CreateTeamCommand("");

    @Test
    void testCreateTeam__validCreateTeam__mustReturns201() {
        given()
                .body(VALID_CREATE_TEAM_MOCK)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .when()
                .post("/rest/v1/teams")
                .then()
                .statusCode(CREATED);
    }

    @Test
    void testCreateTeam__invalidCreateTeam__mustReturns400() {
        given()
                .body(INVALID_CREATE_TEAM_MOCK)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .when()
                .post("/rest/v1/teams")
                .then()
                .statusCode(BAD_REQUEST);
    }
}
