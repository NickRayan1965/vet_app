package com.vet.clinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class VetClinicServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VetClinicServiceApplication.class, args);
	}

}
