package com.teamtbd.cosmetics;

import com.teamtbd.cosmetics.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableConfigurationProperties(AppProperties.class)
@SpringBootApplication
public class CosmeticsBackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(CosmeticsBackendApplication.class, args);
	}

}
