package com.salesianostriana.dam;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.salesianostriana.dam.service.UsuarioService;

@SpringBootApplication
public class BocatappApplication {

	public static void main(String[] args) {
		SpringApplication.run(BocatappApplication.class, args);
	}
	@Bean
	public CommandLineRunner init(ApplicationContext context, UsuarioService usuarioService,
			BCryptPasswordEncoder encriptar) {
		return args -> {
			usuarioService.findAll().stream().forEach(x -> {
				x.setPassword(encriptar.encode(x.getPassword()));
				usuarioService.save(x);

			});
		};
	}


}
