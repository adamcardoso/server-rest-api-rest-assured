package adam.project.client.produto;

import adam.project.model.produto.ProdutoModel;
import adam.project.specs.produto.ProdutoSpecs;
import adam.project.utils.Auth;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ProdutoClient {
    private static final String obterTokenComoAdmin = Auth.obterTokenComoAdmin();
    private static final String obterTokenNaoSendoAdmin = Auth.obterTokenNaoSendoAdmin();
    private static final String obterTokenInvalido = Auth.tokenInvalido();
    private static final String PRODUTOS = "/produtos";
    private static final String PRODUTOS_POR_ID = "/produtos/{_id}";
    private static final String AUTHORIZATION = "Authorization";

    public ProdutoClient() {

    }

    // POST
    public Response cadastrarProduto(ProdutoModel produto){
        return
                given()
                        .header(AUTHORIZATION, obterTokenComoAdmin)
                        .spec(ProdutoSpecs.produtoReqSpec())
                        .body(produto)
                        .when()
                        .post(PRODUTOS);
    }

    public Response cadastrarProdutoDuplicado(ProdutoModel produtoDuplicado) {
        return
                given()
                        .header(AUTHORIZATION, obterTokenComoAdmin)
                        .spec(ProdutoSpecs.produtoReqSpec())
                        .body(produtoDuplicado)
                        .when()
                        .post(PRODUTOS);
    }

    public Response cadastrarProdutoNaoSendoAdmin(ProdutoModel produto){
        return
                given()
                        .header(AUTHORIZATION, obterTokenNaoSendoAdmin)
                        .spec(ProdutoSpecs.produtoReqSpec())
                        .body(produto)
                        .when()
                        .post(PRODUTOS);
    }

    public Response cadastrarProdutoComTokenInvalido(ProdutoModel produto){
        return
                given()
                        .header(AUTHORIZATION, obterTokenInvalido)
                        .spec(ProdutoSpecs.produtoReqSpec())
                        .body(produto)
                        .when()
                        .post(PRODUTOS);
    }

    // PUT
    public Response atualizarProduto(String id, ProdutoModel produto){
        return
                given()
                        .header(AUTHORIZATION, obterTokenComoAdmin)
                        .spec(ProdutoSpecs.produtoReqSpec())
                        .pathParam("_id", id)
                        .body(produto)
                        .when()
                        .put(PRODUTOS_POR_ID);
    }

    public Response atualizarProdutoNaoSendoAdmin(String id, ProdutoModel produto){
        return
                given()
                        .header(AUTHORIZATION, obterTokenNaoSendoAdmin)
                        .spec(ProdutoSpecs.produtoReqSpec())
                        .pathParam("_id", id)
                        .body(produto)
                        .when()
                        .put(PRODUTOS_POR_ID);
    }

    public Response atualizarProdutoComTokenInvalido(ProdutoModel produto){
        return
                given()
                        .header(AUTHORIZATION, obterTokenInvalido)
                        .spec(ProdutoSpecs.produtoReqSpec())
                        .body(produto)
                        .when()
                        .post(PRODUTOS);
    }

    // GET
    public Response buscarProdutoPorId(String id){
        return
                given()
                        .header(AUTHORIZATION, obterTokenComoAdmin)
                        .spec(ProdutoSpecs.produtoReqSpec())
                        .pathParam("_id", id)
                        .when()
                        .get(PRODUTOS_POR_ID);
    }

    public Response buscarTodosOsProdutos(){
        return
                given()
                        .header(AUTHORIZATION, obterTokenComoAdmin)
                        .spec(ProdutoSpecs.produtoReqSpec())
                        .when()
                        .get(PRODUTOS);
    }

    public Response buscarProdutoPorIdInvalido(String id){
        return
                given()
                        .header(AUTHORIZATION, obterTokenComoAdmin)
                        .spec(ProdutoSpecs.produtoReqSpec())
                        .pathParam("_id", id)
                        .when()
                        .get(PRODUTOS_POR_ID);
    }

    public Response buscarProdutoPorIdFormatoInvalido(int id) {
        return given()
                .header(AUTHORIZATION, obterTokenComoAdmin)
                .spec(ProdutoSpecs.produtoReqSpec())
                .pathParam("_id", id)
                .when()
                .get(PRODUTOS_POR_ID);
    }

    // DELETE
    public Response excluirProdutoPorId(String id){
        return
                given()
                        .header(AUTHORIZATION, obterTokenComoAdmin)
                        .spec(ProdutoSpecs.produtoReqSpec())
                        .pathParam("_id", id)
                        .when()
                        .delete(PRODUTOS_POR_ID);
    }

    public Response excluirProdutoNaoSendoAdmin(String id){
        return
                given()
                        .header(AUTHORIZATION, obterTokenNaoSendoAdmin)
                        .spec(ProdutoSpecs.produtoReqSpec())
                        .pathParam("_id", id)
                        .when()
                        .delete(PRODUTOS_POR_ID);
    }

    public Response excluirProdutoComTokenInvalido(String id){
        return
                given()
                        .header(AUTHORIZATION, obterTokenInvalido)
                        .spec(ProdutoSpecs.produtoReqSpec())
                        .pathParam("_id", id)
                        .when()
                        .delete(PRODUTOS_POR_ID);
    }
}
