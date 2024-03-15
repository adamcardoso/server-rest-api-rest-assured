package produto;

import adam.project.client.produto.ProdutoClient;
import adam.project.data.factory.produto.ProdutoDataFactory;
import adam.project.model.produto.ProdutoModel;
import adam.project.model.produto.ProdutoResponse;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static adam.project.model.enums.UserRoles.ADMIN;

class ProdutoContratoTest {
    private static final ProdutoClient produtoClient = new ProdutoClient();

    @Test
    void testValidarContratoDeEditarProduto() {
        ProdutoModel produto = ProdutoDataFactory.produtoValido();
        ProdutoResponse produtoCadastrado = produtoClient.cadastrarProduto(produto, ADMIN).as(ProdutoResponse.class);
        String produtoId = produtoCadastrado.get_id();

        ProdutoResponse produtoResponse = produtoClient.atualizarProduto(produtoId, ProdutoDataFactory.produtoValido(), ADMIN)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/editar_produto.json"))
                .extract().as(ProdutoResponse.class);
    }
}