package hello.memberjoinservice.member.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "members")
@Getter
@NoArgsConstructor
public class MyMemberDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Pattern(regexp = "^[a-z0-9]{4,10}$")
    private String username;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$")
    private String password;

}
