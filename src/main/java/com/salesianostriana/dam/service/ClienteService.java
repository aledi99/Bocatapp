package com.salesianostriana.dam.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.dto.CreateClienteDto;
import com.salesianostriana.dam.model.Avatar;
import com.salesianostriana.dam.model.Cliente;
import com.salesianostriana.dam.model.Role;
import com.salesianostriana.dam.repository.ClienteRepository;

@Service
public class ClienteService extends BaseService<Cliente, Long, ClienteRepository> {
	

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


	public Cliente findFirstByEmail(String email) {
		return this.repositorio.findFirstByEmail(email);
	}

	public Cliente newCliente(CreateClienteDto createClienteDto, Avatar avatar) {
			
		Set<Role> roles = new HashSet<Role>();
		
		roles.add(Enum.valueOf(Role.class, "CLIENTE"));


		Cliente newCliente = Cliente.builder()
				.username(createClienteDto.getUsername())
				.email(createClienteDto.getEmail())
				.password(encoder.encode(createClienteDto.getPassword()))
				.apellidos(createClienteDto.getApellidos())
				.nombre(createClienteDto.getNombre())
				.edad(createClienteDto.getEdad())
				.roles(roles)
				.avatar(avatar)
				.validado(true)
				.build();
				

		return this.repositorio.save(newCliente);
	}
	



}
