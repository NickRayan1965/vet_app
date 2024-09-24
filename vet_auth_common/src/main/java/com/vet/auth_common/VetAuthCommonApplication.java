package com.vet.auth_common;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;

@SpringBootApplication(
    exclude = {R2dbcAutoConfiguration.class}
)
public class VetAuthCommonApplication {

}
