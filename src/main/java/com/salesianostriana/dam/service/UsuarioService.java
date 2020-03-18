package com.salesianostriana.dam.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.dto.CreateUserDto;
import com.salesianostriana.dam.model.Role;
import com.salesianostriana.dam.model.Usuario;
import com.salesianostriana.dam.repository.UsuarioRepository;
import com.salesianostriana.dam.security.ServerSecurityConfig;

@Service
public class UsuarioService extends BaseService<Usuario, Long, UsuarioRepository> {
	
	private ServerSecurityConfig security;
	

	
	public Usuario findFirstByEmail(String email) {
		return this.repositorio.findFirstByEmail(email);
	}
	
	public Usuario newUser(CreateUserDto createUserDto) {
		
		String[] roles = createUserDto.getRoles().split(" ,");
		
		Set<Role> setroles = new HashSet<Role>();
		
		for( String s: roles) {
			Role rol = Enum.valueOf(Role.class, s);
			setroles.add(rol);
		}
		
		Usuario newUser = Usuario.builder()
								.username(createUserDto.getUsername())
								.email(createUserDto.getEmail())
								.password(security.passwordEncoder().encode(createUserDto.getPassword()))
								.roles(setroles)
								.build();
		
		return this.repositorio.save(newUser);
	}

}
