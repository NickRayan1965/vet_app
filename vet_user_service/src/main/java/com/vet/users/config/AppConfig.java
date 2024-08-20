package com.vet.users.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"com.vet.commons.advice"})
@PropertySource("classpath:security.properties")
public class AppConfig {}
