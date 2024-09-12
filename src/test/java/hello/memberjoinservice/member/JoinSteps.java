package hello.memberjoinservice.member;

import hello.memberjoinservice.member.domain.MemberJoinRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class JoinSteps {
    public static ExtractableResponse<Response> 회원가입요청(MemberJoinRequest request) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/members/join")
                .then()
                .log().all().extract();
    }

    public static MemberJoinRequest 회원가입요청_생성() {
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
