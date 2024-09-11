package hello.memberjoinservice.member;

class MemberJoinService {
    private final JoinRepository joinRepository;

    MemberJoinService(final JoinRepository joinRepository) {
        this.joinRepository = joinRepository;
    }

    public void signUp(MemberJoinRequest request) {

        MyMember member = new MyMember(request.username(), request.password());
        joinRepository.save(member);
    }
}
