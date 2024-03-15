package usuario;

import adam.project.client.usuario.UsuarioClient;
import adam.project.data.factory.usuario.UsuarioDataFactory;
import adam.project.model.usuario.UsuarioModel;
import adam.project.model.usuario.UsuarioResponse;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsuarioFuncionalTest {
    UsuarioClient usuarioClient = new UsuarioClient();

    @Test
    public void testCadastrarUsuarioComSucesso() {
        UsuarioModel usuario = UsuarioDataFactory.usuarioValido();

        UsuarioResponse usuarioResult = usuarioClient.cadastrarUsuario(usuario)
                .then()
                .log().all()
                .statusCode(201)
                .extract().as(UsuarioResponse.class);

        assertAll("usuarioResult",
                () -> assertEquals("Cadastro realizado com sucesso", usuarioResult.getMessage()),
                () -> assertNotNull(usuarioResult.get_id())
        );
    }

    @Test
    public void testCadastrarUsuarioComDadosAusentes() {
        UsuarioModel usuarioComDadosAusentes = UsuarioDataFactory.usuarioComDadosAusente();

        UsuarioResponse usuarioResponse = usuarioClient.cadastrarUsuario(usuarioComDadosAusentes)
                .then()
                .log().all()
                .statusCode(400)
                .extract().as(UsuarioResponse.class)
                ;

        assertAll("usuarioResponse",
                () -> assertEquals("nome não pode ficar em branco", usuarioResponse.getNome()),
                () -> assertEquals( "email não pode ficar em branco" , usuarioResponse.getEmail()),
                () -> assertEquals("password não pode ficar em branco", usuarioResponse.getPassword()),
                () -> assertEquals("administrador deve ser 'true' ou 'false'", usuarioResponse.getAdministrador())
        );
    }

    @Test
    public void testEditarUsuarioComSucesso() {

        UsuarioResponse usuarioCadastrado = usuarioClient.cadastrarUsuario(
                UsuarioDataFactory.usuarioValido()).as(UsuarioResponse.class);

        String usuarioCadastradoId = usuarioCadastrado.get_id();

        usuarioClient.atualizarUsuario(usuarioCadastrado.get_id(), UsuarioDataFactory.usuarioValido())
                .then()
                .log().all()
                .statusCode(200)
                .body("message", equalTo("Registro alterado com sucesso"))
        ;

        System.out.println("ID do Usuario sendo editado: " + usuarioCadastradoId);
    }
}
