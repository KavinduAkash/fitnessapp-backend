package com.sliit.fitnessbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = {"com.sliit.fitnessbackend", "com.sliit.fitnessbackend.controller"})
@EnableAsync
@EnableTransactionManagement
@EnableJpaAuditing
public class FitnessBackendApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(FitnessBackendApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
