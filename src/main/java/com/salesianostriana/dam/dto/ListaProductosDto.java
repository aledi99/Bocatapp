package com.salesianostriana.dam.dto;

import com.salesianostriana.dam.dto.ListaEstablecimientoDto.ListaEstablecimientoDtoBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ListaProductosDto {
	
	private long id;
	private String nombre;
	private String precio;
	private boolean activo;
	private boolean gluten;
	private boolean lactosa;

}