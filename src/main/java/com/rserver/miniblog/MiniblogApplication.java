package com.rserver.miniblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MiniblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniblogApplication.class, args);
	}

}
