package com.crio.stayEase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class StayEaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(StayEaseApplication.class, args);
	}

}
