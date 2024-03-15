package adam.project.specs.carrinho;

import adam.project.client.auth.AuthClient;
import adam.project.specs.InicialSpecs;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class CarrinhoSpecs {
    private static final AuthClient loginClient = new AuthClient();
    private static final String AUTHORIZATION = "authorization";

    private CarrinhoSpecs() {
    }

    public static RequestSpecification carrinhoReqSpec() {
        return new RequestSpecBuilder()
                .addRequestSpecification(InicialSpecs.setUp())
                .addHeader(AUTHORIZATION, loginClient.tokenAdm())
                .setContentType(ContentType.JSON)
                .build();
    }
}
