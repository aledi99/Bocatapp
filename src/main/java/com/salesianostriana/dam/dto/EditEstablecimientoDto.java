package com.salesianostriana.dam.dto;

import com.salesianostriana.dam.dto.EditProductoDto.EditProductoDtoBuilder;
import com.salesianostriana.dam.model.Categoria;
import com.salesianostriana.dam.model.Ubicacion;

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
	private String horaApertura,horaCierre;
	private Ubicacion localizacion;
	private Categoria categoria;
	private double presupuesto;
	private double latitud, longitud;
	private boolean abierto;
	private String nombreCategoria;


}
