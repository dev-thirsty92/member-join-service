package hello.memberjoinservice.member.repository;

import hello.memberjoinservice.member.domain.MyMemberDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoinRepository extends JpaRepository<MyMemberDto, String> {
}
