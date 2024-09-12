package hello.memberjoinservice.member.repository;

import hello.memberjoinservice.member.domain.MyMemberDto;

public interface JoinRepository {
    void save(MyMemberDto member);
}
