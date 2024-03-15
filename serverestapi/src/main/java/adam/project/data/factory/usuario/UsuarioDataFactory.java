package adam.project.data.factory.usuario;

import adam.project.model.usuario.UsuarioModel;
import net.datafaker.Faker;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;
import java.util.Random;

public class UsuarioDataFactory {
    private static final Faker faker = new Faker(new Locale("PT-BR"));
    private static final Random geradorBoolean = new Random();

    private UsuarioDataFactory() {

    }

    public static UsuarioModel usuarioValido() {
        return novoUsuario();
    }

    public static UsuarioModel usuarioComDadosAusente() {
        UsuarioModel usuarioSemDados = novoUsuario();
        usuarioSemDados.setNome(StringUtils.EMPTY);
        usuarioSemDados.setEmail(StringUtils.EMPTY);
        usuarioSemDados.setPassword(StringUtils.EMPTY);
        usuarioSemDados.setAdministrador(StringUtils.EMPTY);

        return usuarioSemDados;
    }

    private static UsuarioModel novoUsuario() {
        UsuarioModel novoUsuario = new UsuarioModel();
        novoUsuario.setNome(faker.name().firstName() + " " + faker.name().lastName());
        novoUsuario.setEmail(faker.internet().emailAddress());
        novoUsuario.setPassword(faker.internet().password());
        novoUsuario.setAdministrador(String.valueOf(geradorBoolean.nextBoolean()));

        return novoUsuario;
    }

    public static UsuarioModel usuarioComNomeVazio() {
        UsuarioModel usuarioModel = novoUsuario();
        usuarioModel.setNome(StringUtils.EMPTY);
        return usuarioModel;
    }

    public static UsuarioModel usuarioComEmailNaoPreenchido() {
        UsuarioModel usuarioModel = novoUsuario();
        usuarioModel.setEmail(StringUtils.EMPTY);
        return usuarioModel;
    }

    public static UsuarioModel usuarioComSenhaEmBranco() {
        UsuarioModel usuarioModel = novoUsuario();
        usuarioModel.setPassword(StringUtils.EMPTY);
        return usuarioModel;
    }

    public static UsuarioModel usuarioComRoleInvalida() {
        UsuarioModel usuarioModel = novoUsuario();
        usuarioModel.setAdministrador(StringUtils.EMPTY);
        return usuarioModel;
    }
}
