package com.salesianostriana.dam.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class EditProductoDto {

	private String nombre;
	private String descripcion;
	private double precio;
	private boolean glucosa;
	private boolean lactosa;
}
