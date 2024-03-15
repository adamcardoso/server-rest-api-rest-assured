package adam.project.specs.produto;

import adam.project.specs.InicialSpecs;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ProdutoSpecs {

    private ProdutoSpecs(){

    }

    public static RequestSpecification produtoReqSpec(){
        return new RequestSpecBuilder()
                .addRequestSpecification(InicialSpecs.setUp())
                .setContentType(ContentType.JSON)
                .build();
    }
}
