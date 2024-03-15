package adam.project.model.produto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListaDeProdutos {
    private int quantidade;
    private List<ProdutoResponse> produtos;
}
