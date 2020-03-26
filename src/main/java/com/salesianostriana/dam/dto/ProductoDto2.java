package com.salesianostriana.dam.dto;

import com.salesianostriana.dam.dto.ProductoDto.ProductoDtoBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ProductoDto2 {
	private long id;
	private String nombre;
	private String descripcion;
	private double precio;
	private boolean activo;
	private boolean glucosa;
	private boolean lactosa;
	
	private EstablecimientoDto establecimiento;
	private ImagenDto imagen;
}
