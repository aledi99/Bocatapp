package com.salesianostriana.dam.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CreateLocalizacionDto {
	
	private String latitud;
	private String longitud;
	
	

}
