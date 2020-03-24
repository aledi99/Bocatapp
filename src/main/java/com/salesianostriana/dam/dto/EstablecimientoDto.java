package com.salesianostriana.dam.dto;

import java.time.LocalTime;

import com.salesianostriana.dam.dto.ProductoDto2.ProductoDto2Builder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class EstablecimientoDto {
	private long id;
	private String nombre;
	private String descripcion;
	private boolean abierto;
	private float valoracion;
	private LocalTime horaApertura;
	private LocalTime horaCierre;

}
