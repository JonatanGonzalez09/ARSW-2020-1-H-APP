package edu.eci.arsw.happapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"edu.eci.arsw.happ"})
public class HappAPIApplication {
	public static void main(String[] args) {
		SpringApplication.run(HappAPIApplication.class, args);
	}
}