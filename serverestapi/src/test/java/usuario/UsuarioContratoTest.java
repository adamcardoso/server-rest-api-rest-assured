package usuario;

import adam.project.client.usuario.UsuarioClient;
import adam.project.data.factory.usuario.UsuarioDataFactory;
import adam.project.model.usuario.UsuarioModel;
import adam.project.model.usuario.UsuarioResponse;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class UsuarioContratoTest {

    private static final UsuarioClient usuarioClient = new UsuarioClient();

    @Test
    void testValidarContratoBuscaPorID() {
        UsuarioModel usuario = UsuarioDataFactory.usuarioValido();

        UsuarioResponse usuarioCadastrar = usuarioClient.cadastrarUsuario(usuario).as(UsuarioResponse.class);

        usuarioClient.buscarUsuario(usuarioCadastrar.get_id())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/buscar_usuario_id.json"));
    }
}
