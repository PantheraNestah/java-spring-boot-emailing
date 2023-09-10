package com.SpringBootEmailing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EntityScan(basePackages = "com.SpringBootEmailing.entities")
@EnableAsync
public class EmailingApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailingApplication.class, args);
	}

}
