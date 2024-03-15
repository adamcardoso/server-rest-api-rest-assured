package adam.project.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.config;

public class InicialSpecs {

    private InicialSpecs() {
    }

    public static RequestSpecification setUp(){
        return new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(3000)
                .setConfig(config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
                .build();
    }
}
