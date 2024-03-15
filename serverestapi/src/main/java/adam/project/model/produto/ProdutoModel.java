package adam.project.model.produto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoModel {
    private String nome;
    private Integer preco;
    private String descricao;
    private Integer quantidade;
}
