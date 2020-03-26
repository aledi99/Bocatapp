package com.salesianostriana.dam.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ListUsersDto {
	
	private long id;
	private String nombre;
	private String username;
	private String email;


}
