package adam.project.data.factory.produto;

import adam.project.model.produto.ProdutoModel;
import net.datafaker.Faker;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

public class ProdutoDataFactory {
    private static final Faker faker = new Faker(new Locale("PT-BR"));

    private static final String ID_INVALIDO = "sfsfwrwr";
    private static final Integer NEGATIVE_NUMBER = -1;

    private ProdutoDataFactory(){

    }

    public static ProdutoModel produtoValido() {
        return novoProduto();
    }

    public static ProdutoModel produtoInvalido(){
        return produtoDuplicado(produtoExistente());
    }

    private static ProdutoModel novoProduto() {
        ProdutoModel novoProduto = new ProdutoModel();

        novoProduto.setNome(faker.commerce().productName());
        novoProduto.setPreco(faker.number().numberBetween(1, 1000));
        novoProduto.setDescricao(faker.commerce().brand());
        novoProduto.setQuantidade(faker.number().numberBetween(1, 1000));

        return novoProduto;
    }

    public static ProdutoModel produtoExistente() {
        ProdutoModel produtoExistente = novoProduto();

        produtoExistente.setNome("Teclado Kumara Azul Com vermelho");

        return produtoExistente;
    }

    public static ProdutoModel produtoDuplicado(ProdutoModel produtoExistente) {
        ProdutoModel produtoDuplicado = new ProdutoModel();
        produtoDuplicado.setNome(produtoExistente.getNome());
        produtoDuplicado.setPreco(faker.number().numberBetween(1, 1000));
        produtoDuplicado.setDescricao(faker.commerce().brand());
        produtoDuplicado.setQuantidade(faker.number().numberBetween(1, 1000));

        return produtoDuplicado;
    }

    public static String idInvalido() {
        return ID_INVALIDO;
    }

    public static ProdutoModel produtoComPrecoNegativo() {
        ProdutoModel produto = novoProduto();

        produto.setPreco(NEGATIVE_NUMBER);

        return produto;
    }

    public static ProdutoModel produtoComQuantidaNegativa() {
        ProdutoModel produto = novoProduto();

        produto.setQuantidade(NEGATIVE_NUMBER);

        return produto;
    }

    public static ProdutoModel produtoComNomeVazio() {
        ProdutoModel produto = novoProduto();

        produto.setNome(StringUtils.EMPTY);

        return produto;
    }

    public static ProdutoModel produtoComDescricaoVazia(){
        ProdutoModel produto = novoProduto();

        produto.setDescricao(StringUtils.EMPTY);

        return produto;
    }
}
