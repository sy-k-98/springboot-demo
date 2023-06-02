package com.example.demo;

import com.example.demo.models.Customer;
import com.example.demo.repositories.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello() {
		return "hello world";
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return args -> {
			repository.save(new Customer("leo", "aa"));
			repository.save(new Customer("you", "bb"));
			repository.save(new Customer("jun", "cc"));

			log.info("findAll---------------------");
			for (Customer customer: repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			log.info("findById--------------------");
			Customer customer = repository.findById(1L);
			log.info(customer.toString());
			log.info("");

			log.info("findByLastName--------------------");
			repository.findByLastName("aa").forEach(c -> {log.info(c.toString());});
			log.info("");

		};
	}

}
