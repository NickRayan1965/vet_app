package com.vet.clinic.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.vet.commons.advice"})
public class AppConfig {
    
}
