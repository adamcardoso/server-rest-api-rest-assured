package adam.project.client.usuario;

import adam.project.model.usuario.UsuarioModel;
import adam.project.specs.usuario.UsuarioSpecs;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UsuarioClient {

    private static final String USUARIOS = "/usuarios";
    private static final String USUARIOS_POR_ID = "/usuarios/{_id}";

    public UsuarioClient() {}

    public Response cadastrarUsuario(UsuarioModel usuario) {
        return
                given()
                        .spec(UsuarioSpecs.usuarioReqSpec())
                        .body(usuario)
                        .when()
                        .post(USUARIOS)
                ;
    }

    public Response atualizarUsuario(String id, UsuarioModel usuario){
        return
                given()
                        .spec(UsuarioSpecs.usuarioReqSpec())
                        .pathParam("_id", id)
                        .body(usuario)
                        .when()
                        .put(USUARIOS_POR_ID)
                ;
    }

    public Response buscarUsuario(String id){
        return
                given()
                        .spec(UsuarioSpecs.usuarioReqSpec())
                        .pathParam("_id", id)
                        .when()
                        .get(USUARIOS_POR_ID)
                ;
    }

    public Response excluirUsuario(String id){
        return
                given()
                        .spec(UsuarioSpecs.usuarioReqSpec())
                        .pathParam("_id", id)
                        .when()
                        .delete(USUARIOS_POR_ID)
                ;
    }
}
