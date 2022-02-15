package com.teamtbd.cosmetics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication


public class CosmeticsBackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(CosmeticsBackendApplication.class, args);
	}

}
