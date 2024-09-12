package hello.memberjoinservice.member;

interface JoinRepository {
    void save(MyMemberDto member);
}
