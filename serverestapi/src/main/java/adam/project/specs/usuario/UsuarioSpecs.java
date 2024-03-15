package adam.project.specs.usuario;

import adam.project.specs.InicialSpecs;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class UsuarioSpecs {
    private UsuarioSpecs(){

    }

    public static RequestSpecification usuarioReqSpec(){
        return new RequestSpecBuilder()
                .addRequestSpecification(InicialSpecs.setUp())
                .setContentType(ContentType.JSON)
                .build();
    }
}
