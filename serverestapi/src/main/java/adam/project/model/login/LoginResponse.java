package adam.project.model.login;

import lombok.Data;

@Data
public class LoginResponse extends LoginModel{
    private String message;
    private String authorization;
}
