package com.vet.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class VetUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VetUserServiceApplication.class, args);
	}

}
