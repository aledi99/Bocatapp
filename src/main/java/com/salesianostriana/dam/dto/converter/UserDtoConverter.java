package com.salesianostriana.dam.dto.converter;

import org.springframework.stereotype.Component;

import com.salesianostriana.dam.dto.UserDto;
import com.salesianostriana.dam.model.Usuario;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserDtoConverter {
	
	private static String rol;
	
	public UserDto convertUserEntityToUserDto(Usuario user) {
		rol = "CLIENTE";
		return UserDto.builder()
				.username(user.getUsername())
				.rol(rol)
				.build();
	}
}
