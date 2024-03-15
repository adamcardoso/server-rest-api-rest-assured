package produto;

import adam.project.client.produto.ProdutoClient;
import adam.project.data.factory.produto.ProdutoDataFactory;
import adam.project.model.produto.ListaDeProdutos;
import adam.project.model.produto.ProdutoModel;
import adam.project.model.produto.ProdutoResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProdutoFuncionalTest {
    ProdutoClient produtoClient = new ProdutoClient();


    // TESTE PARAMETRIZADO
    @ParameterizedTest
    @MethodSource("adam.project.data.provider.ProdutoTestData#dadosParaTesteCadastroProduto")
    void testCadastroProdutoComCenariosNegativos(ProdutoModel produto, int statusCodeEsperado, String mensagemEsperada, String key) {
        produtoClient.cadastrarProduto(produto)
                .then()
                .log().all()
                .statusCode(statusCodeEsperado)
                .body(key, equalTo(mensagemEsperada));
    }

    // TESTES VÁLIDOS
    // POST
    @Test
    void testDadoUsuarioAdministradorQuandoCadastroProdutoEntaoStatusCode201() {
        // Cadastrar o produto
        ProdutoModel produto = ProdutoDataFactory.produtoValido();

        ProdutoResponse produtoResponse = produtoClient.cadastrarProduto(produto)
                .then()
                .log().all()
                .statusCode(201)
                .extract().as(ProdutoResponse.class);

        // Excluir o produto cadastrado
        Response response = produtoClient.excluirProdutoPorId(produtoResponse.get_id());

        response.then().statusCode(200);
    }

    // PUT
    @Test
    void testDadoUsuarioAdministradorQuandoEditaProdutoEntaoStatusCode200() {
        // Cadastrar o produto
        ProdutoResponse produtoCadastrado = produtoClient.cadastrarProduto(
                ProdutoDataFactory.produtoValido()).as(ProdutoResponse.class);

        // Editar o produto cadastrado
        produtoClient.atualizarProduto(produtoCadastrado.get_id(), ProdutoDataFactory.produtoValido())
                .then()
                .log().all()
                .statusCode(200)
                .body("message", equalTo("Registro alterado com sucesso"));

        // Excluir o produto editado
        produtoClient.excluirProdutoPorId(produtoCadastrado.get_id())
                .then()
                .log().all()
                .statusCode(200)
                .body("message", equalTo("Registro excluído com sucesso"));
    }

    // GET
    @Test
    void testDadoUsuarioAdministradorQuandoBuscaProdutoPorIdEntaoStatusCode200() {
        // Cadastrar o produto
        ProdutoModel cadastrarProduto = ProdutoDataFactory.produtoValido();

        ProdutoResponse produtoCadastrado = produtoClient.cadastrarProduto(cadastrarProduto)
                .then()
                .statusCode(201)
                .extract().as(ProdutoResponse.class);

        try {
            // Buscar o produto por ID
            ProdutoResponse produtoBuscadoPorId = produtoClient.buscarProdutoPorId(produtoCadastrado.get_id())
                    .then()
                    .log().all()
                    .statusCode(200)
                    .extract().as(ProdutoResponse.class);

            // Verificar se os detalhes correspondem
            Assertions.assertEquals(cadastrarProduto.getNome(), produtoBuscadoPorId.getNome());
            Assertions.assertEquals(cadastrarProduto.getPreco(), produtoBuscadoPorId.getPreco());
            Assertions.assertEquals(cadastrarProduto.getDescricao(), produtoBuscadoPorId.getDescricao());
            Assertions.assertEquals(cadastrarProduto.getQuantidade(), produtoBuscadoPorId.getQuantidade());
        } finally {
            // Excluir o produto independentemente do resultado do teste
            produtoClient.excluirProdutoPorId(produtoCadastrado.get_id());
        }
    }

    @Test
    void testDadoUsuarioAdministradorQuandoListoTodosOsProdutosStatusCode200() {

        ListaDeProdutos listaDeProdutos = produtoClient.buscarTodosOsProdutos()
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(ListaDeProdutos.class);

        Assertions.assertNotNull(listaDeProdutos);
        Assertions.assertTrue(listaDeProdutos.getQuantidade() >= 0);
    }

    // DELETE
    @Test
    void testDadoQueOUsuarioADministradorQuandoExcluirProdutoComSucessoStatusCode200() {
        // Cadastrar o produto
        ProdutoModel produto = ProdutoDataFactory.produtoValido();

        ProdutoResponse produtoCadastrado = produtoClient.cadastrarProduto(produto)
                .then()
                .log().all()
                .statusCode(201)
                .extract().as(ProdutoResponse.class);

        // Excluir o produto
        Response response = produtoClient.excluirProdutoPorId(produtoCadastrado.get_id());

        // Verificar se o produto foi excluído corretamente
        ProdutoResponse produtoExcluido = response
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(ProdutoResponse.class);

        Assertions.assertEquals("Registro excluído com sucesso", produtoExcluido.getMessage());
    }

    // TESTES NEGATIVO POST
    @Test
    void testDadoUsuarioAdministradorQuandoCadastroProdutoComNomeExistenteEntaoStatusCode400() {
        ProdutoModel produtoExistente = ProdutoDataFactory.produtoInvalido();
        produtoClient.cadastrarProduto(produtoExistente);

        ProdutoModel produtoDuplicado = ProdutoDataFactory.produtoDuplicado(produtoExistente);

        ProdutoResponse produtoResponse = produtoClient.cadastrarProdutoDuplicado(produtoDuplicado)
                .then()
                .log().all()
                .statusCode(400)
                .extract().as(ProdutoResponse.class);

        Assertions.assertEquals("Já existe produto com esse nome", produtoResponse.getMessage());
    }

    @Test
    void testDadoUsuarioNaoAdministradorQuandoCadastroProdutoEntaoStatusCode401() {
        ProdutoModel produto = ProdutoDataFactory.produtoValido();

        ProdutoResponse produtoResponse = produtoClient.cadastrarProdutoNaoSendoAdmin(produto)
                .then()
                .log().all()
                .statusCode(403)
                .extract().as(ProdutoResponse.class);

        Assertions.assertEquals("Rota exclusiva para administradores", produtoResponse.getMessage());
    }

    @Test
    void testDadoTokenInvalidoQuandoCadastroProdutoEntaoStatusCode401() {

        ProdutoModel produto = ProdutoDataFactory.produtoValido();

        ProdutoResponse produtoResponse = produtoClient.cadastrarProdutoComTokenInvalido(produto)
                .then()
                .log().all()
                .statusCode(401)
                .extract().as(ProdutoResponse.class);

        Assertions.assertEquals("Token de acesso ausente, inválido, expirado ou usuário do token não existe mais", produtoResponse.getMessage());
    }

    // TESTES NEGATIVO PUT
    @Test
    void testDadoUsuarioNaoAdministradorQuandoEditoProdutoEntaoStatusCode403() {
        ProdutoResponse produtoCadastrado = produtoClient.cadastrarProduto(
                ProdutoDataFactory.produtoValido()).as(ProdutoResponse.class);

        String produtoId = produtoCadastrado.get_id();

        ProdutoResponse produtoResponse = produtoClient.atualizarProdutoNaoSendoAdmin(produtoCadastrado.get_id(), ProdutoDataFactory.produtoValido())
                .then()
                .log().all()
                .statusCode(403)
                .extract().as(ProdutoResponse.class);

        assertNotNull(produtoId);
        Assertions.assertEquals("Rota exclusiva para administradores", produtoResponse.getMessage());
    }

    @Test
    void testDadoTokenInvalidoQuandoEditoProdutoEntaoStatusCode401() {
        ProdutoResponse produtoCadastrado = produtoClient.cadastrarProduto(
                ProdutoDataFactory.produtoValido()).as(ProdutoResponse.class);

        String produtoId = produtoCadastrado.get_id();

        ProdutoResponse produtoResponse = produtoClient.atualizarProdutoComTokenInvalido(ProdutoDataFactory.produtoValido())
                .then()
                .log().all()
                .statusCode(401)
                .extract().as(ProdutoResponse.class);

        assertNotNull(produtoId);
        Assertions.assertEquals("Token de acesso ausente, inválido, expirado ou usuário do token não existe mais", produtoResponse.getMessage());
    }

    // TESTES NEGATIVOS DELETE
    @Test
    void testDadoUsuarioNaoAdministradorQuandoExcluirProdutoEntaoStatusCode403() {
        ProdutoModel produto = ProdutoDataFactory.produtoValido();

        // Cadastrar o produto
        ProdutoResponse produtoCadastrado = produtoClient.cadastrarProduto(produto)
                .then()
                .log().all()
                .statusCode(201)
                .extract().as(ProdutoResponse.class);

        Response response = produtoClient.excluirProdutoNaoSendoAdmin(produtoCadastrado.get_id());

        ProdutoResponse produtoExcluido = response
                .then()
                .log().all()
                .statusCode(403)
                .extract().as(ProdutoResponse.class);

        Assertions.assertEquals("Rota exclusiva para administradores", produtoExcluido.getMessage());
    }

    @Test
    void testDadoTokenInvalidoQuandoExcluirProdutoEntaoStatusCode401() {
        ProdutoModel produto = ProdutoDataFactory.produtoValido();

        // Cadastrar o produto
        ProdutoResponse produtoCadastrado = produtoClient.cadastrarProduto(produto)
                .then()
                .log().all()
                .statusCode(201)
                .extract().as(ProdutoResponse.class);

        ProdutoResponse produtoResponse = produtoClient.excluirProdutoComTokenInvalido(produtoCadastrado.get_id())
                .then()
                .log().all()
                .statusCode(401)
                .extract().as(ProdutoResponse.class);

        Assertions.assertEquals("Token de acesso ausente, inválido, expirado ou usuário do token não existe mais", produtoResponse.getMessage());
    }


    // TESTES NEGATIVOS GET
    @Test
    void testDadoUsuarioAdministradorQuandoBuscaProdutoPorIdInvalidoEntaoStatusCode400() {
        ProdutoModel cadastrarProduto = ProdutoDataFactory.produtoValido();

        ProdutoResponse produtoCadastrado = produtoClient.cadastrarProduto(cadastrarProduto)
                .then()
                .log().all()
                .statusCode(201)
                .extract().as(ProdutoResponse.class);

        assertNotNull(produtoCadastrado.get_id());

        // Buscar o produto por ID inválido
        ProdutoResponse produtoResponse = produtoClient.buscarProdutoPorIdInvalido(ProdutoDataFactory.idInvalido())
                .then()
                .log().all()
                .statusCode(400)
                .extract().as(ProdutoResponse.class);

        Assertions.assertEquals("Produto não encontrado", produtoResponse.getMessage());
    }

    @Test
    void testDadoUsuarioAdministradorQuandoBuscaProdutoComIdFormatoInvalidoEntaoStatusCode400() {
        ProdutoModel cadastrarProduto = ProdutoDataFactory.produtoValido();

        ProdutoResponse produtoCadastrado = produtoClient.cadastrarProduto(cadastrarProduto)
                .then()
                .log().all()
                .statusCode(201)
                .extract().as(ProdutoResponse.class);

        assertNotNull(produtoCadastrado.get_id());

        // Buscar o produto por ID com formato incorreto (número inteiro)
        ProdutoResponse produtoResponse = produtoClient.buscarProdutoPorIdFormatoInvalido(ProdutoDataFactory.idInvalidoFormatoInvalido())
                .then()
                .log().all()
                .statusCode(400).extract()
                .as(ProdutoResponse.class);

        Assertions.assertEquals("Produto não encontrado", produtoResponse.getMessage());
    }
}
