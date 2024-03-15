package usuario;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class UsuarioContratoTest {

    @BeforeEach
    public void setUp() {
        baseURI = "http://localhost:3000";
    }

    @Test
    public void testValidarContratoBuscarPorID() {
        String idUsuario = "WSY0jqfMsu9aWNDC";

        given()
                .log().all()
                .pathParam("_id", idUsuario)
                .when()
                .get("/usuarios/{_id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/buscar_usuario_id.json"));
    }
}
