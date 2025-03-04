package com.example.kakfademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class KakfaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KakfaDemoApplication.class, args);
	}

}
