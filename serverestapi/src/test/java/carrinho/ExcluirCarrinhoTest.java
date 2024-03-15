package carrinho;

import adam.project.client.carrinho.CarrinhoClient;
import adam.project.data.factory.carrinho.CarrinhoDataFactory;
import adam.project.model.carrinho.CarrinhoModel;
import adam.project.model.enums.UserRoles;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class ExcluirCarrinhoTest {
    private final CarrinhoClient carrinhoClient = new CarrinhoClient();

    @Test
    public void testConcluirCompraComSucesso(){
        CarrinhoModel carrinho = new CarrinhoModel();
        carrinho.setProdutos(CarrinhoDataFactory.listaCarrinhoValido());
        carrinhoClient.concluirCompra(carrinho)
                .then()
                .statusCode(200)
                .body("message",equalTo("Registro excluído com sucesso"))
        ;
    }
    @Test
    public void testCancelarCompraComSucesso(){
        CarrinhoModel carrinho = new CarrinhoModel();
        carrinho.setProdutos(CarrinhoDataFactory.listaCarrinhoValido());
        carrinhoClient.cancelarCompra(carrinho)
                .then()
                .statusCode(200)
                .body("message",equalTo("Registro excluído com sucesso. Estoque dos produtos reabastecido"))
        ;
    }
}
