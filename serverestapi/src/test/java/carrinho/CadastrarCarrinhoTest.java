package carrinho;

import adam.project.client.carrinho.CarrinhoClient;
import adam.project.data.factory.carrinho.CarrinhoDataFactory;
import adam.project.model.carrinho.CarrinhoModel;
import adam.project.model.carrinho.CarrinhoResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CadastrarCarrinhoTest {
    private final CarrinhoClient carrinhoClient = new CarrinhoClient();

    @Test
    void testCadastrarCarrinhoComProdutoExistente() {
        CarrinhoModel carrinho = new CarrinhoModel();
        carrinho.setProdutos(CarrinhoDataFactory.listaCarrinhoValido());

        CarrinhoResponse carrinhoResponse = carrinhoClient.cadastrarCarrinho(carrinho)
                .then()
                .log().all()
                .statusCode(201)
                .extract().as(CarrinhoResponse.class);

        Assertions.assertEquals("Cadastro realizado com sucesso", carrinhoResponse.getMessage());

        carrinhoClient.cancelarCompra(carrinho);
    }
}
