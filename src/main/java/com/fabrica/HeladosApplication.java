package com.fabrica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication

@EnableCaching
public class HeladosApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeladosApplication.class, args);
	}

}
