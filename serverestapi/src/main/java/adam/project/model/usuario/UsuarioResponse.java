package adam.project.model.usuario;

import lombok.Data;

@Data
public class UsuarioResponse extends UsuarioModel{
    private String message;
    private String _id;
}
