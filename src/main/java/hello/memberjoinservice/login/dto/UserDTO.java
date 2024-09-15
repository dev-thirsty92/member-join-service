package hello.memberjoinservice.login.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    @NotNull
    @Pattern(regexp = "^[a-z0-9]{4,10}$")
    private String username;
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$")
    private String password;

}
