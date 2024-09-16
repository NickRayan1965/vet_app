package com.vet.apigateway.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@Configuration
public class SpringSecurityConfig {
    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .authorizeExchange(
                    authorizeExchange -> authorizeExchange
                        .pathMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                        .anyExchange().authenticated()
                    )
                .csrf(csrf -> csrf.disable())
                .formLogin(formLogin-> formLogin.disable())
                .logout(logout -> logout.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                .build();
    }
}
