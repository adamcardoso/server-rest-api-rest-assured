package adam.project.model.carrinho;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCarrinhoModel {
    private String idProduto;
    private Integer quantidade;
}
