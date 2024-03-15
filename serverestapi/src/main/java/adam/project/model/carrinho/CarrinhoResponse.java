package adam.project.model.carrinho;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarrinhoResponse extends CarrinhoModel{
    private Integer quantidade;
    private List<CarrinhoModel> carrinhos;
    private Integer precoTotal;
    private Integer quantidadeTotal;
    private String idUsuario;
    private String _id;
    private String message;
}
