package produto;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;

public class ProdutoContratoTest {
    private String token;

    private static String idProduto = "2iy1SnZmsMuTWnbE";

    @BeforeEach
    public void setUp() {
        baseURI = "http://localhost";
        port = 3000;
    }

    private String obterToken(String email, String senha) {
        return given()
                .body(
                        """
                                {
                          "email": "%s",
                          "password": "%s"
                        }
                        """.formatted(email, senha)
                )
                .contentType(ContentType.JSON)
                .when()
                .post("/login")
                .then()
                .log().all()
                .extract()
                .path("authorization");
    }

    @Test
    public void testValidarContratoDeEditarProduto() {
        token = obterToken("ronaldinho@gmail.com", "teste");

        given()
                .log().all()
                .header("Authorization", token)
                .pathParam("_id", idProduto)
                .contentType("application/json")
                .body("""
                {
                  "nome": "Teclado Kumarass Preto222 com Vermelho",
                  "preco": 250,
                  "descricao": "Teclado Mecanico",
                  "quantidade": 453
                }
                """)
                .when()
                .put("/produtos/{_id}")
                .then()
                .log().all()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/editar_produto.json"));
    }

    @Test
    public void testValidarContratoBuscarPorID() {
        String idProduto = "016rCXOF5Fzjcu2Q";

        given()
                .log().all()
                .pathParam("_id", idProduto)
                .when()
                .get("/produtos/{_id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/buscar_produto_id.json"));
    }
}
