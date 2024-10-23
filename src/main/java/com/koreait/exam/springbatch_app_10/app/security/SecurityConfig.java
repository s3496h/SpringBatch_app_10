package com.koreait.exam.springbatch_app_10.app.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity // 웹 보안 활성화 -> Spring Security 기본설정 사용
@EnableGlobalMethodSecurity(prePostEnabled = true)// 메서드 단위 보안 설정 // 특정 메서드에 권한 부여 및 제한
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationSuccessHandler authenticationSuccessHandler; // 인증 성공시
    private final AuthenticationFailureHandler authenticationFailureHandler; // 인증 실패시

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(
                        formLogin -> formLogin
                                .loginPage("/member/login") // 로그인 페이지(GET) 페이지의 URL 설정
                                .loginProcessingUrl("/member/login") // 로그인 처리 URL (POST) 로그인 처리 URL 설정
                                .successHandler(authenticationSuccessHandler)
                                .failureHandler(authenticationFailureHandler)
                )
                .logout(
                        logout -> logout.logoutUrl("/member/logout")
                );
        return http.build();
    }
}
