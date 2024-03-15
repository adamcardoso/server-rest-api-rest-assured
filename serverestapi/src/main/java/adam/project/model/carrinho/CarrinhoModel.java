package adam.project.model.carrinho;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarrinhoModel {
    private List<ItemCarrinhoModel> produtos = new ArrayList<>();
}
