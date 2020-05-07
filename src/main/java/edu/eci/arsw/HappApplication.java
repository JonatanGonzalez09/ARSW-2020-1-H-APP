package edu.eci.arsw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HappApplication {

	public static void main(String[] args) {
		SpringApplication.run(HappApplication.class, args);
	}

}