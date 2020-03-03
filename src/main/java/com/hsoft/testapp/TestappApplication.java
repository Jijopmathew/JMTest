package com.hsoft.testapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TestappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestappApplication.class, args);
	}

}
