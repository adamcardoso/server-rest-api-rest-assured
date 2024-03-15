package adam.project.model.produto;

import lombok.Data;

@Data
public class ProdutoResponse extends ProdutoModel{
    private String _id;
    private String message;
}
