package com.vet.users.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
public class MainSecurity {
    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
            .csrf(csrfSpec -> csrfSpec.disable())
            .formLogin(formLoginSpec -> formLoginSpec.disable())
            .httpBasic(httpBasicSpec -> httpBasicSpec.disable())
            .logout(logoutSpec -> logoutSpec.disable())
            .build();
    }
}
