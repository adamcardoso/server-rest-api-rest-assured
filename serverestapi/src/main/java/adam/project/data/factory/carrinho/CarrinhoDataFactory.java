package adam.project.data.factory.carrinho;

import adam.project.client.produto.ProdutoClient;
import adam.project.data.factory.produto.ProdutoDataFactory;
import adam.project.model.carrinho.ItemCarrinhoModel;
import adam.project.model.produto.ProdutoModel;
import adam.project.model.produto.ProdutoResponse;

import java.util.ArrayList;
import java.util.List;

import static adam.project.model.enums.UserRoles.ADMIN;

public class CarrinhoDataFactory {
    private static final ProdutoClient produtoClient = new ProdutoClient();

    private CarrinhoDataFactory(){

    }

    public static List<ItemCarrinhoModel> listaCarrinhoValido(){
        ProdutoModel produto = ProdutoDataFactory.produtoValido();
        ProdutoResponse produtoResponse = produtoClient.cadastrarProduto(produto, ADMIN).as(ProdutoResponse.class);
        ItemCarrinhoModel carrinhoProduto = new ItemCarrinhoModel(produtoResponse.get_id(), produto.getQuantidade());

        List<ItemCarrinhoModel> listaCarrinho = new ArrayList<>();

        listaCarrinho.add(carrinhoProduto);

        return listaCarrinho;
    }
}
