package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GenericProducerOnlyApplication {

	public static void main(String[] args) {
		SpringApplication.run(GenericProducerOnlyApplication.class, args);
		System.out.println("GENERIC PRODUCER STARTED:\n");
	}

}
