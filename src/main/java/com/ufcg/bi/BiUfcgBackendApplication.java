package com.ufcg.bi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BiUfcgBackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(BiUfcgBackendApplication.class, args);
	}

}
