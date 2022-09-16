package com.andrelas1.pwmanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class PwmanagerApplication {

	private static final Logger log = LoggerFactory.getLogger(PwmanagerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PwmanagerApplication.class);
	}

	@Bean
	public CommandLineRunner start(PasswordRepository repository) {
		return (args) -> {
			// save a few customers
			repository.save(new Password("Jack", "Bauer"));
			repository.save(new Password("Chloe", "O'Brian"));
			repository.save(new Password("Kim", "Bauer"));
			repository.save(new Password("David", "Palmer"));
			repository.save(new Password("Michelle", "Dessler"));

			// fetch all customers
			log.info("Passwords found with findAll():");
			log.info("-------------------------------");
			for (Password customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Password customer = repository.findById(1L);
			log.info("Password found with findById(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			// fetch customers by last name
			log.info("Password found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByApplicationName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});
			// for (Password bauer : repository.findByLastName("Bauer")) {
			//  log.info(bauer.toString());
			// }
			log.info("");
		};
	}

}
