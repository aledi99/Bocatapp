package com.salesianostriana.dam;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.salesianostriana.dam.service.ClienteService;

@SpringBootApplication
public class BocatappApplication {

	public static void main(String[] args) {
		SpringApplication.run(BocatappApplication.class, args);
	}
	@Bean
	public CommandLineRunner init(ApplicationContext context, ClienteService clienteService,
			BCryptPasswordEncoder encriptar) {
		return args -> {
			clienteService.findAll().stream().forEach(x -> {
				x.setPassword(encriptar.encode(x.getPassword()));
				clienteService.save(x);

			});
		};
	}


}
