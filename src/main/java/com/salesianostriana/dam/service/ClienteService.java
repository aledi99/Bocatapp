package com.salesianostriana.dam.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.dto.CreateClienteDto;
import com.salesianostriana.dam.dto.CreateUserDto;
import com.salesianostriana.dam.model.Cliente;
import com.salesianostriana.dam.model.Role;
import com.salesianostriana.dam.model.Usuario;
import com.salesianostriana.dam.repository.ClienteRepository;
import com.salesianostriana.dam.repository.UsuarioRepository;
import com.salesianostriana.dam.security.ServerSecurityConfig;

@Service
public class ClienteService extends BaseService<Cliente, Long, ClienteRepository> {

	private ServerSecurityConfig security;



	public Cliente findFirstByEmail(String email) {
		return this.repositorio.findFirstByEmail(email);
	}

	public Cliente newCliente(CreateClienteDto createClienteDto) {
			
		Set<Role> roles = new HashSet<Role>();
		
		roles.add(Enum.valueOf(Role.class, "CLIENTE"));


		Cliente newCliente = Cliente.builder()
				.username(createClienteDto.getUsername())
				.email(createClienteDto.getEmail())
				.password(createClienteDto.getPassword())
				.apellidos(createClienteDto.getApellidos())
				.nombre(createClienteDto.getNombre())
				.edad(createClienteDto.getEdad())
				.roles(roles)
				.validado(true)
				.build();
				

		return this.repositorio.save(newCliente);
	}



}
