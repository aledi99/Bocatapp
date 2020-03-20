package com.salesianostriana.dam.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CreateClienteDto {
	
	private String email;
	private String username;
	private String password;
	private String nombre;
	private String apellidos;
	private int edad;
}
