package com.project.workmanagemantSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.project.workmanagemantSystem.utils.RandomCodeGenerator.generateRandomCode;

@SpringBootApplication
public class WorkManagementSystem {

	public static void main(String[] args) {

		SpringApplication.run(WorkManagementSystem.class, args);
	}

}
