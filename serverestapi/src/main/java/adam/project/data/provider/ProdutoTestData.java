package adam.project.data.provider;

import adam.project.data.factory.produto.ProdutoDataFactory;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class ProdutoTestData {

    private static final String NAME = "nome";
    private static final String MESSAGE = "message";
    private static final String DESCRIPTION = "descricao";
    private static final String QUANTITY = "quantidade";
    private static final String PRICE = "preco";
    private static final String EMPTY_NAME = "nome não pode ficar em branco";
    private static final String EMPTY_DESCRIPTION = "descricao não pode ficar em branco";
    private static final String NEGATIVE_PRICE = "preco deve ser um número positivo";
    private static final String NEGATIVE_QUANTITY = "quantidade deve ser maior ou igual a 0";
    private static final String SAME_NAME = "Já existe produto com esse nome";


    public static Stream<Arguments> dadosParaTesteCadastroProduto() {
        return Stream.of(
                // Produto com nome nulo
                Arguments.of(ProdutoDataFactory.produtoComNomeVazio(), 400, EMPTY_NAME, NAME),

                // Produto inválido com descrição vazia
                Arguments.of(ProdutoDataFactory.produtoComDescricaoVazia(), 400, EMPTY_DESCRIPTION, DESCRIPTION),

                // Produto com preço negativo
                Arguments.of(ProdutoDataFactory.produtoComPrecoNegativo(), 400, NEGATIVE_PRICE, PRICE),

                // Produto com quantidade negativa
                Arguments.of(ProdutoDataFactory.produtoComQuantidaNegativa(), 400, NEGATIVE_QUANTITY, QUANTITY),

                // Produto com nome já existente
                Arguments.of(ProdutoDataFactory.produtoExistente(), 400, SAME_NAME, MESSAGE)
        );
    }
}
