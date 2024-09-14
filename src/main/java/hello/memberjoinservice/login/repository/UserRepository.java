package hello.memberjoinservice.login.repository;

import hello.memberjoinservice.login.entitiy.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
