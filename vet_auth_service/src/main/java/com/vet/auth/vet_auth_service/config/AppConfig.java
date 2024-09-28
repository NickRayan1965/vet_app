package com.vet.auth.vet_auth_service.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:security.properties")
@ComponentScan(basePackages = {"com.vet.commons.util", "com.vet.commons.advice", "com.vet.auth_common.mappers"})
public class AppConfig {
    
}
