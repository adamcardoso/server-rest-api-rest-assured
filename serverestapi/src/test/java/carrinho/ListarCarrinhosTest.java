package carrinho;

import adam.project.client.carrinho.CarrinhoClient;
import adam.project.model.carrinho.CarrinhoResponse;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ListarCarrinhosTest {
    private final CarrinhoClient carrinhoClient = new CarrinhoClient();

    @Test
    void testBuscarCarrinhoPorId() {
        carrinhoClient.buscarCarrinhoPorId("qbMqntef4iTOwWfg")
                .then()
                .statusCode(200)
                .body("idUsuario",equalTo("oUb7aGkMtSEPf6BZ"));
    }

    @Test
    void testBuscarTodosOsCarrinhosUsandoQuery() {
        CarrinhoResponse response = carrinhoClient.buscarTodosOsCarrinhos("_id","aFOUqntef4iaOwWfg")
                .then()
                .statusCode(200)
                .extract().as(CarrinhoResponse.class);
        assertEquals(0, response.getQuantidade());
    }
}
