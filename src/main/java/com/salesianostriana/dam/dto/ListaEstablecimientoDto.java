package com.salesianostriana.dam.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ListaEstablecimientoDto {
	
	private long id;
	private String nombre;
	private String descripcion;
	private CategoriaDto categoria;

}
