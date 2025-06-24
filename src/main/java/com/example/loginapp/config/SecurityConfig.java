package com.example.loginapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // POST 테스트를 위해 임시로 CSRF 끔
                .authorizeHttpRequests()
                .requestMatchers("/api/user/register", "/api/user/signin").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().disable(); // 폼 로그인 비활성화

        return http.build();
    }
}