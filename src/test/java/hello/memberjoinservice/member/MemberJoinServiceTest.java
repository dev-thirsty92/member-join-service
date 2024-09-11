package hello.memberjoinservice.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberJoinServiceTest {

    private MemberJoinService memberJoinService;
    private JoinRepository joinRepository;

    @BeforeEach
    void setUp() {
        joinRepository = new JoinRepositoryImpl();
        memberJoinService = new MemberJoinService(joinRepository);
    }

    @Test
    void 회원가입() {
        final MemberJoinRequest request = 회원가입요청_생성();
        memberJoinService.signUp(request);
    }

    private static MemberJoinRequest 회원가입요청_생성() {
        String username = "user00";
        String password = "passWord00";
        return new MemberJoinRequest(username, password);
    }

    @Test
    void 회원가입_중복() {
        final MemberJoinRequest request1 = 회원가입요청_생성();
        // db에 중복된 username이 없으므로 회원을 저장
        memberJoinService.signUp(request1);

        // db에 중복된 username이 있으므로 저장 실패
        final MemberJoinRequest request2 = 회원가입요청_생성();
        memberJoinService.signUp(request2);

    }

}
