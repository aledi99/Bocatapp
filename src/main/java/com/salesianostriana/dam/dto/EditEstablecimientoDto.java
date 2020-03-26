package com.salesianostriana.dam.dto;

import com.salesianostriana.dam.dto.EditProductoDto.EditProductoDtoBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class EditEstablecimientoDto {
	
	private String nombre,descripcion;
	private double presupuesto;
	private String horaApertura,horaCierre;
	private double lat, longitud;
	private boolean abierto;
	private String nombreCategoria;

}
