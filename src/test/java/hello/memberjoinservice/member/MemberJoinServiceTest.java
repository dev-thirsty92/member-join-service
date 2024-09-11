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
        String username = "user00";
        String password = "passWord00";
        MemberJoinRequest request = new MemberJoinRequest(username, password);
        memberJoinService.signUp(request);
    }

    @Test
    void 회원가입_중복() {
        String username = "user00";
        String password = "passWord00";
        // db에 중복된 username이 없으므로 회원을 저장
        MemberJoinRequest request1 = new MemberJoinRequest(username, password);
        memberJoinService.signUp(request1);

        // db에 중복된 username이 있으므로 저장 실패
        MemberJoinRequest request2 = new MemberJoinRequest(username, password);
        memberJoinService.signUp(request2);

    }

}
