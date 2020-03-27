package com.salesianostriana.dam.conversor;

import org.springframework.stereotype.Component;

import com.salesianostriana.dam.dto.ListUsersDto;
import com.salesianostriana.dam.model.Cliente;
import com.salesianostriana.dam.model.Gerente;
import com.salesianostriana.dam.model.Usuario;

@Component
public class ConversorUsuario {
	
	public ListUsersDto converterListaClientesDto(Cliente cliente) {
		
		return ListUsersDto.builder()
				.id(cliente.getId())
				.nombre(cliente.getNombre())
				.username(cliente.getUsername())
				.email(cliente.getEmail())
				.build();
				
	}
	
public ListUsersDto converterListaGerentesDto(Gerente gerente) {
		
		return ListUsersDto.builder()
				.id(gerente.getId())
				.nombre(gerente.getNombre())
				.username(gerente.getUsername())
				.email(gerente.getEmail())
				.build();
				
	}

}
