package hello.memberjoinservice.login.entitiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//table명을 user 라고 하면 동작하지 않음. 예약어 관련 이슈가 있지않을까 예상함
//@Table(name = "users")
@Getter
@Setter
public class UserEntity {

    @Id
    //id값이 겹치지 않게 잘 생성해줌
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    private String role;
}
