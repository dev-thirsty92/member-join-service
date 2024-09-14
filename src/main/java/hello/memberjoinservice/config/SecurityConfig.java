package hello.memberjoinservice.config;

import hello.memberjoinservice.jwt.JWTFilter;
import hello.memberjoinservice.jwt.JWTUtil;
import hello.memberjoinservice.jwt.LoginFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //frontend server 구현시 필요한 cors 설정
        // 참고용
        http
                .cors((cors) -> cors
                        .configurationSource(new CorsConfigurationSource() {
                            @Override
                            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                                CorsConfiguration config = new CorsConfiguration();

                                config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                                config.setAllowedMethods(Collections.singletonList("*"));
                                config.setAllowCredentials(true);
                                config.setAllowedHeaders(Collections.singletonList("*"));
                                config.setMaxAge(3600L);
                                config.setExposedHeaders(Collections.singletonList("Authorization"));
                                return config;
                            }
                        }));

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
                        .requestMatchers("/login", "/", "/members/join", "/join").permitAll()
                        // /admin 요청에 대한 접근은 `admin`권한을 가진 사용자에게 허용
                        .requestMatchers("/admin").hasRole("ADMIN")
                        //토큰 재발행 요청에 대해 모든 권한 허용
                        .requestMatchers("/reissue").permitAll()
                        // 나머지 요청은 login한 사용자만 접근할 수 있습니다.
                        .anyRequest().authenticated()
                );

        //필터를 추가한다.
        http
                .addFilterBefore(new JWTFilter(jwtUtil),LoginFilter.class); //loginFilter 앞에서 발동한다.
        http
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class);



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

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();

    }

}
