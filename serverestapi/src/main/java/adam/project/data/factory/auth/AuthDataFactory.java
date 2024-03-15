package adam.project.data.factory.auth;

import adam.project.model.login.LoginModel;
import adam.project.utils.Manipulation;

import java.util.Properties;

public class AuthDataFactory {
    public AuthDataFactory() {

    }

    public static LoginModel obterTokenComoAdmin() {
        Properties props = Manipulation.getProp();

        return new LoginModel(props.getProperty("admin.email"), props.getProperty("admin.password"));
    }

    public static LoginModel obterTokenNaoSendoAdmin() {
        Properties props = Manipulation.getProp();

        return new LoginModel(props.getProperty("nonadmin.email"), props.getProperty("nonadmin.password"));
    }

    public static LoginModel loginInvalido(){
        return new LoginModel("123456@gmail.com","123Teste123");
    }

    public static LoginModel loginValido(){
        return obterTokenComoAdmin();
    }
}
