package com.salesianostriana.dam.dto;

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
	private String horaApertura;
	private String horaCierre;

}
