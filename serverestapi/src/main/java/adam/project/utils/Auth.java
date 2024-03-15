package adam.project.utils;

import adam.project.client.auth.AuthClient;
import adam.project.model.login.LoginModel;
import io.restassured.response.Response;

public class Auth {

    private static final AuthClient authClient = new AuthClient();
    private static final String TOKEN_INVALIDO = "token_invalido";

    private Auth() {

    }

    public static String obterToken(LoginModel login) {
        // Obtém o token usando o método logar da classe AuthClient
        Response response = authClient.logar(login);

        // Extrai o token da resposta
        return extrairToken(response);
    }

    public static String obterTokenComoAdmin() {
        LoginModel loginModel = new LoginModel();

        loginModel.setEmail(Manipulation.getProp().getProperty("admin.email"));
        loginModel.setPassword(Manipulation.getProp().getProperty("admin.password"));

        return obterToken(loginModel);
    }

    public static String obterTokenNaoSendoAdmin() {
        LoginModel loginModel = new LoginModel();

        loginModel.setEmail(Manipulation.getProp().getProperty("nonadmin.email"));
        loginModel.setPassword(Manipulation.getProp().getProperty("nonadmin.password"));

        return obterToken(loginModel);
    }

    public static String tokenInvalido() {
        return TOKEN_INVALIDO;
    }

    private static String extrairToken(Response response) {
        return response.path("authorization");
    }
}
