package adam.project.client.auth;

import adam.project.model.login.LoginModel;
import adam.project.specs.auth.AuthSpecs;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthClient {
    private static final String LOGIN_ENDPOINT = "/login";

    public AuthClient() {
    }

    public Response logar(LoginModel login) {
        return given()
                .spec(AuthSpecs.authReqSpec())
                .body(login)
                .when()
                .post(LOGIN_ENDPOINT)
                .then()
                .extract()
                .response();
    }
}
