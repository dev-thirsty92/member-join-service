package hello.memberjoinservice.login;

import groovy.util.logging.Slf4j;
import hello.memberjoinservice.ApiTest;
import hello.memberjoinservice.jwt.JWTUtil;
import hello.memberjoinservice.login.dto.LoginRequest;
import hello.memberjoinservice.login.dto.MemberJoinRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class LoginServiceTest extends ApiTest {

    @Autowired
    private JWTUtil jwtUtil;

    @Test
    void 로그인성공() {

        String username= "user00";
        String password= "passWord00";

//        LoginRequest request = new LoginRequest(username, password);

        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .multiPart("username", username, ContentType.TEXT.toString())
                .multiPart("password", password, ContentType.TEXT.toString())
//                .body(request)
                .when()
                .post("/login")
                .then()
                .log().all().extract();

        // 로그인 성공 시 발급받은 토큰을 response header로부터 가져온다.
        String token = response.header("Authorization").split(" ")[1];
        // jwtUtil을 이용하여 토큰을 복호화하여
        // username과 로그인한 username이 일치하는지 검증한다.
        assertThat(jwtUtil.getUsername(token)).isEqualTo(username);

    }

    @Test
    void 로그인실패() {

        String username= "invalid";
        String password= "wrong";

//        LoginRequest request = new LoginRequest(username, password);

        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .multiPart("username", username, ContentType.TEXT.toString())
                .multiPart("password", password, ContentType.TEXT.toString())
//                .body(request)
                .when()
                .post("/login")
                .then()
                .log().all().extract();
        assertThat(response.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());

    }

}
