package adam.project.model.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioModel {
    private String nome;
    private String email;
    private String password;
    private String administrador;
}
