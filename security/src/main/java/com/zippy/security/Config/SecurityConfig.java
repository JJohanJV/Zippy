package com.zippy.security.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception
    {
        return  http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authRequest-> authRequest.requestMatchers(
                                "/auth/**",
                                "/api/v1/demo",
                                "/api/v1/user/new"
                        ).permitAll()
                        .anyRequest().authenticated())
                .build();

    }
}
