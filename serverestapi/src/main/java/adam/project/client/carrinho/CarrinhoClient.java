package adam.project.client.carrinho;

import adam.project.model.carrinho.CarrinhoModel;
import adam.project.specs.carrinho.CarrinhoSpecs;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CarrinhoClient {
    private static final String CADASTRAR_CARRRINHO = "/carrinhos";
    private static final String LISTAR_CARRRINHOS_CADASTRAD0S = "/carrinhos";
    private static final String LISTAR_CARRINHO_POR_ID = "/carrinhos/{_id}";
    private static final String EXCLUIR_E_CANCELAR_COMPRA = "/carrinhos/cancelar-compra";
    private static final String EXCLUIR_CARRINHO_AO_CONCLUIR_COMPRA = "/carrinhos/concluir-compra";

    public CarrinhoClient() {
    }

    public Response buscarCarrinhoPorId(String id){
        return
                given()
                        .spec(CarrinhoSpecs.carrinhoReqSpec())
                        .pathParam("_id", id)
                    .when()
                        .get(LISTAR_CARRINHO_POR_ID);
    }

    public Response buscarTodosOsCarrinhos(String key, String id){
        return
                given()
                        .queryParam(key,id)
                        .spec(CarrinhoSpecs.carrinhoReqSpec())
                    .when()
                        .get(LISTAR_CARRRINHOS_CADASTRAD0S);
    }

    public Response cadastrarCarrinho(CarrinhoModel carrinho){
        return
                given()
                        .spec(CarrinhoSpecs.carrinhoReqSpec())
                        .body(carrinho)
                        .when()
                        .post(CADASTRAR_CARRRINHO);
    }

    public Response concluirCompra(CarrinhoModel listaCarrinho){
        String token =
                cadastrarCarrinho(listaCarrinho)
                        .then()
                        .extract().response().jsonPath().getString("authorization");
        return
                given()
                        .spec(CarrinhoSpecs.carrinhoReqSpec())
                        .header("authorization", "Bearer " + token)
                        .when()
                        .delete(EXCLUIR_CARRINHO_AO_CONCLUIR_COMPRA);
    }
    public Response cancelarCompra(CarrinhoModel listaCarrinho){
        String token =
                cadastrarCarrinho(listaCarrinho)
                        .then()
                        .extract().response().jsonPath().getString("authorization");
        return
                given()
                        .spec(CarrinhoSpecs.carrinhoReqSpec())
                        .header("authorization", "Bearer " + token)
                        .when()
                        .delete(EXCLUIR_E_CANCELAR_COMPRA);
    }
}
