package hello.memberjoinservice.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

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
        MemberJoinRequest request = new MemberJoinRequest(username, password);
        memberJoinService.signUp(request);
    }

    private record MemberJoinRequest(String username, String password) {

        private MemberJoinRequest {
            int upperCnt = 0;
            int lowerCnt = 0;
            int digitCnt = 0;

            // username 검증
            int userNameSize = username.length();
            if (userNameSize < 4 || userNameSize > 10) {
                throw new IllegalArgumentException("username length must be between 4 and 10");
            }

            for (char c : username.toCharArray()) {
                if (!Character.isLetterOrDigit(c) || Character.isUpperCase(c)) {
                    throw new IllegalArgumentException("username contains invalid characters");
                }

                if (Character.isLowerCase(c)) {
                    lowerCnt++;
                    continue;
                }
                if (Character.isDigit(c)) {
                    digitCnt++;
                }
            }

            int usernameResult = lowerCnt * digitCnt;
            if (usernameResult == 0) {
                throw new IllegalArgumentException("username must consist of lowercase letters and numbers.");
            }


            //password 검증

            lowerCnt = 0;
            digitCnt = 0;
            int passwordSize = password.length();
            if (passwordSize < 8 || passwordSize > 15) {
                throw new IllegalArgumentException("password length must be between 8 and 15");
            }


            for (char c : password.toCharArray()) {

                if (!Character.isLetterOrDigit(c)) {
                    throw new IllegalArgumentException("username contains invalid characters");
                }

                if (Character.isUpperCase(c)) {
                    upperCnt++;
                    continue;
                }
                if (Character.isLowerCase(c)) {
                    lowerCnt++;
                    continue;
                }
                if (Character.isDigit(c)) {
                    digitCnt++;
                }
            }

            int result = upperCnt * lowerCnt * digitCnt;
            if (result == 0) {
                throw new IllegalArgumentException("password must consist of upper case, lower case, and number");
            }

        }
    }

    private class MemberJoinService {
        private final JoinRepository joinRepository;

        private MemberJoinService(final JoinRepository joinRepository) {
            this.joinRepository = joinRepository;
        }

        public void signUp(MemberJoinRequest request) {

            MyMember member = new MyMember(request.username(), request.password());
            joinRepository.save(member);
        }
    }

    private static class MyMember {
        private final String username;
        private final String password;

        public MyMember(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }
    }

    private interface JoinRepository {
        void save(MyMember member);
    }

    private class JoinRepositoryImpl implements JoinRepository {
        Map<String, MyMember> persistence = new HashMap<>();

        @Override
        public void save(MyMember member) {
            if (!persistence.containsKey(member.getUsername())) {
                persistence.put(member.getUsername(), member);
            }
        }
    }
}
