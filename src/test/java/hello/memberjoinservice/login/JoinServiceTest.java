package hello.memberjoinservice.login;

import hello.memberjoinservice.ApiTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class JoinServiceTest extends ApiTest {

    @Test
    void 회원가입() {
        final var request = JoinSteps.회원가입요청_생성("user00","passWord00");
        final var response = JoinSteps.회원가입요청(request);
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void 회원가입_검증실패(){
        final var request = JoinSteps.회원가입요청_생성("us0","pa");
        final var response = JoinSteps.회원가입요청(request);
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }


    @Test
    void 회원가입_중복() {

        회원가입();
        final var request = JoinSteps.회원가입요청_생성("user00","passWord00");
        final var  response = JoinSteps.회원가입요청(request);
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }


}
