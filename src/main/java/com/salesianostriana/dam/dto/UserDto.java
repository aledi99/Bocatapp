package com.salesianostriana.dam.dto;

import java.util.Set;

import com.salesianostriana.dam.model.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UserDto {
	
	private String username;
	private String rol;

}