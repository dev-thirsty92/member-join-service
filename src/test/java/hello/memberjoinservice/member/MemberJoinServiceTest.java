package hello.memberjoinservice.member;

import hello.memberjoinservice.ApiTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class MemberJoinServiceTest extends ApiTest {

    @Test
    void 회원가입() {
        final var  request = JoinSteps.회원가입요청_생성();
        final var response = JoinSteps.회원가입요청(request);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void 회원가입_중복() {
        final var request = JoinSteps.회원가입요청_생성();
        회원가입();
        final var  response = JoinSteps.회원가입요청(request);
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }


}
