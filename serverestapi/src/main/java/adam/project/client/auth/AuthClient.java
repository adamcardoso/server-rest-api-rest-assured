package adam.project.client.auth;

import adam.project.data.factory.auth.AuthDataFactory;
import adam.project.model.login.LoginModel;
import adam.project.specs.auth.AuthSpecs;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthClient {
    private static final String LOGIN_ENDPOINT = "/login";
    private static final String AUTHORIZATION = "authorization";

    public AuthClient() {
    }

    public Response logar(LoginModel login) {
        return given()
                .spec(AuthSpecs.authReqSpec())
                .body(login)
                .when()
                .post(LOGIN_ENDPOINT);
    }

    public String tokenAdm(){
        LoginModel login = AuthDataFactory.obterTokenComoAdmin();
        return logar(login)
                .then()
                .extract().path(AUTHORIZATION);
    }
    public String tokenConvidado(){
        LoginModel login = AuthDataFactory.obterTokenNaoSendoAdmin();
        return logar(login)
                .then()
                .extract().path(AUTHORIZATION);
    }
}
