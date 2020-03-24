package com.salesianostriana.dam;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.salesianostriana.dam.files.FileStorageProperties;
import com.salesianostriana.dam.service.AdminService;
import com.salesianostriana.dam.service.ClienteService;
import com.salesianostriana.dam.service.GerenteService;

@SpringBootApplication
@EnableConfigurationProperties({ FileStorageProperties.class })
public class BocatappApplication {

	public static void main(String[] args) {
		SpringApplication.run(BocatappApplication.class, args);
	}
	@Bean
	public CommandLineRunner init(ApplicationContext context, ClienteService clienteService, AdminService adminService, GerenteService gerenteService,
			BCryptPasswordEncoder encriptar) {
		return args -> {
			adminService.findAll().stream().forEach(x -> {
				x.setPassword(encriptar.encode(x.getPassword()));
				adminService.save(x);

			});
			
			gerenteService.findAll().stream().forEach(x -> {
				x.setPassword(encriptar.encode(x.getPassword()));
				gerenteService.save(x);

			});

		};
		
		
	}


}
