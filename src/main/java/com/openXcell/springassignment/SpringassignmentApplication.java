package com.openXcell.springassignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@Author: Afrid Khan Pathan

@SpringBootApplication
@EnableJpaAuditing
public class SpringassignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringassignmentApplication.class, args);
	}

}
