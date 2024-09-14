package hello.memberjoinservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //csrf disable
        // jwt방식은 session을 stateless 상태로 관리하기 떄문에
        // csrf 공격에 대해 방어조치를 하지 않음
        http
                .csrf((auth)-> auth.disable());
        http
                .formLogin((auth)-> auth.disable());
        http
                .httpBasic((auth)-> auth.disable());

        http
                .authorizeHttpRequests((auth)-> auth
                        // /login, /, /members/join 요청에 대해서 모든 권한을 허용
                        .requestMatchers("/login", "/", "/members/join").permitAll()
                        // /admin 요청에 대한 접근은 `admin`권한을 가진 사용자에게 허용
                        .requestMatchers("/admin").hasRole("ADMIN")
                        // 나머지 요청은 login한 사용자만 접근할 수 있습니다.
                        .anyRequest().authenticated()
                );

        // (중요) jwt 방식에서는 session을 statless상태로 관리하게 됩니다.
        http
                .sessionManagement((session)-> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        return http.build();

    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
