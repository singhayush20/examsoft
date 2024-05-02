package com.examsoft.examsoft;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExamsoftApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ExamsoftApplication.class, args);
	}

	@Override
	public void run (String... args) throws Exception {
		System.out.println("Application is running...");
	}
}
