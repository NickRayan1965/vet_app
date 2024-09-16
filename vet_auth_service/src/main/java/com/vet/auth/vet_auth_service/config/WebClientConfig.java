package com.vet.auth.vet_auth_service.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    @LoadBalanced
    WebClient.Builder webClient() {
        return WebClient.builder();
    }

    @Bean
    @Qualifier("userWebClient")
    WebClient userWebClient(WebClient.Builder wBuilder) {
        return wBuilder.baseUrl("http://VET-USER-SERVICE")
        .build();
    }
}
