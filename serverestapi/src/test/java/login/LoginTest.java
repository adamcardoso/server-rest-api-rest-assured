package login;

import adam.project.client.auth.AuthClient;
import adam.project.data.factory.auth.AuthDataFactory;
import adam.project.model.login.LoginModel;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.IsEqual.equalTo;

public class LoginTest {
    private static final AuthClient loginClient = new AuthClient();

    @Test
    public void testLogarComSucesso(){

        LoginModel login = AuthDataFactory.loginValido();
        loginClient.logar(login)
                .then()
                .statusCode(200)
                .body("message",equalTo("Login realizado com sucesso"))
        ;
    }
    @Test
    public void testLogarFuncionarioComSucesso(){
        LoginModel login = AuthDataFactory.loginInvalido();

        loginClient.logar(login)
                .then()
                .statusCode(401)
                .body("message",equalTo("Email e/ou senha inválidos"))
        ;
    }
}
