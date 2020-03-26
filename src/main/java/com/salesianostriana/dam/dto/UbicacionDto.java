package com.salesianostriana.dam.dto;

import com.salesianostriana.dam.dto.GerenteDto2.GerenteDto2Builder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UbicacionDto {
	
	private double longitud;
	private double latitud;

}
