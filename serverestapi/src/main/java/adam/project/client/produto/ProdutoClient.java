package adam.project.client.produto;

import adam.project.model.enums.UserRoles;
import adam.project.model.produto.ProdutoModel;
import adam.project.specs.produto.ProdutoSpecs;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ProdutoClient {
    private static final String PRODUTOS = "/produtos";
    private static final String PRODUTOS_POR_ID = "/produtos/{_id}";

    public ProdutoClient() {

    }

    // POST
    public Response cadastrarProduto(ProdutoModel produto, UserRoles userRoles){
        return
                given()
                        .spec(ProdutoSpecs.produtoReqSpec(userRoles))
                        .body(produto)
                    .when()
                        .post(PRODUTOS);
    }

    public Response cadastrarProdutoDuplicado(ProdutoModel produtoDuplicado, UserRoles userRoles) {
        return
                given()
                        .spec(ProdutoSpecs.produtoReqSpec(userRoles))
                        .body(produtoDuplicado)
                        .when()
                        .post(PRODUTOS);
    }

    // PUT
    public Response atualizarProduto(String id, ProdutoModel produto, UserRoles userRoles){
        return
                given()
                        .spec(ProdutoSpecs.produtoReqSpec(userRoles))
                        .pathParam("_id", id)
                        .body(produto)
                    .when()
                        .put(PRODUTOS_POR_ID);
    }

    // GET
    public Response buscarProdutoPorId(String id){
        return
                given()
                        .spec(ProdutoSpecs.produtoReqSpec())
                        .pathParam("_id", id)
                        .when()
                        .get(PRODUTOS_POR_ID);
    }

    public Response buscarTodosOsProdutos(){
        return
                given()
                        .spec(ProdutoSpecs.produtoReqSpec())
                        .when()
                        .get(PRODUTOS);
    }

    // DELETE
    public Response excluirProdutoPorId(String id, UserRoles userRoles){
        return
                given()
                        .spec(ProdutoSpecs.produtoReqSpec(userRoles))
                        .pathParam("_id", id)
                        .when()
                        .delete(PRODUTOS_POR_ID);
    }
}
