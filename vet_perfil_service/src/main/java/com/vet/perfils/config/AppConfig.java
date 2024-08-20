package com.vet.perfils.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.vet.commons.advice", "com.vet.commons.util"})
public class AppConfig {}
