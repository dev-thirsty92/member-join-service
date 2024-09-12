package hello.memberjoinservice.member;

import hello.memberjoinservice.ApiTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

class MemberJoinServiceTest extends ApiTest {

    @Test
    void 회원가입() {
        final MemberJoinRequest request = 회원가입요청_생성();

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/members/join")
                .then()
                .log().all().extract();
    }


    private static MemberJoinRequest 회원가입요청_생성() {
        /**
         * 검증 테스틑 위해 username, password를 자유로히 변경하여 테스트해주세요.
         */
        String username = "user00";
        String password = "passWord00";

//        String username = "use";
//        String password = "pas";

        return new MemberJoinRequest(username, password);
    }

}
