package com.aluracursos.screenmatch5;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Screenmatch5Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Screenmatch5Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hola mundo desde Spring");

	}
}
