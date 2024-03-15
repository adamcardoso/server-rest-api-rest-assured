package adam.project.specs.produto;

import adam.project.client.auth.AuthClient;
import adam.project.model.enums.UserRoles;
import adam.project.specs.InicialSpecs;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ProdutoSpecs {
    private static final AuthClient loginClient = new AuthClient();
    private static final String AUTHORIZATION = "authorization";

    private ProdutoSpecs(){

    }

    public static RequestSpecification produtoReqSpecAdm(){
        return new RequestSpecBuilder()
                .addRequestSpecification(InicialSpecs.setUp())
                .addHeader(AUTHORIZATION, loginClient.tokenAdm())
                .setContentType(ContentType.JSON)
                .build();
    }

    public static RequestSpecification produtoReqSpecFuncionario() {
        return new RequestSpecBuilder()
                .addRequestSpecification(InicialSpecs.setUp())
                .addHeader(AUTHORIZATION, loginClient.tokenConvidado())
                .setContentType(ContentType.JSON)
                .build()
                ;
    }

    public static RequestSpecification produtoReqSpecTokenInvalido() {
        return new RequestSpecBuilder()
                .addRequestSpecification(InicialSpecs.setUp())
                .addHeader(AUTHORIZATION, "Invalido")
                .setContentType(ContentType.JSON)
                .build()
                ;
    }

    public static RequestSpecification produtoReqSpec() {
        return produtoReqSpecFuncionario();
    }
    public static RequestSpecification produtoReqSpec(UserRoles permissao) {
        return switch (permissao) {
            case ADMIN -> ProdutoSpecs.produtoReqSpecAdm();
            case CONVIDADO -> ProdutoSpecs.produtoReqSpecFuncionario();
            default -> ProdutoSpecs.produtoReqSpecTokenInvalido();
        };
    }
}
