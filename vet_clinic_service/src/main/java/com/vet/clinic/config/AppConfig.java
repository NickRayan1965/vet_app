package com.vet.clinic.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@ComponentScan(basePackages = {"com.vet.commons.advice", "com.vet.commons.mappers"})
public class AppConfig implements WebFluxConfigurer {}
