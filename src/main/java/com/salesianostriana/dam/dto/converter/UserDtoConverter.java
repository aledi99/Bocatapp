package com.salesianostriana.dam.dto.converter;

import org.springframework.stereotype.Component;

import com.salesianostriana.dam.dto.ClienteDto;
import com.salesianostriana.dam.dto.CreateClienteDto;
import com.salesianostriana.dam.dto.UserDto;
import com.salesianostriana.dam.model.Cliente;
import com.salesianostriana.dam.model.Usuario;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserDtoConverter {
	
	
	public CreateClienteDto convertUserEntityToUserDto(Cliente cliente) {
		return CreateClienteDto.builder()
				.username(cliente.getUsername())
				.email(cliente.getEmail())
				.password(cliente.getPassword())
				.build();
	}
}
