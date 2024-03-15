package adam.project.data.provider;

import adam.project.data.factory.usuario.UsuarioDataFactory;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class UsuarioTestData {
    private static final String NAME = "nome";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String ROLE = "administrador";
    private static final String EMPTY_NAME = "nome não pode ficar em branco";
    private static final String EMPTY_EMAIL = "email não pode ficar em branco";
    private static final String EMPTY_PASSWORD = "password não pode ficar em branco";
    private static final String INVALID_ROLE = "administrador deve ser 'true' ou 'false'";


    public static Stream<Arguments> dadosParaTesteCadastroDeUsuárioComDadosAusentes() {
        return Stream.of(
                // Produto com nome nulo
                Arguments.of(UsuarioDataFactory.usuarioComNomeVazio(), 400, EMPTY_NAME, NAME),

                // Produto inválido com descrição vazia
                Arguments.of(UsuarioDataFactory.usuarioComEmailNaoPreenchido(), 400, EMPTY_EMAIL, EMAIL),

                // Produto com preço negativo
                Arguments.of(UsuarioDataFactory.usuarioComSenhaEmBranco(), 400, EMPTY_PASSWORD, PASSWORD),

                // Produto com quantidade negativa
                Arguments.of(UsuarioDataFactory.usuarioComRoleInvalida(), 400, INVALID_ROLE, ROLE)
        );
    }
}
