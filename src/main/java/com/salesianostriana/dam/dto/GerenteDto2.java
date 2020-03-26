package com.salesianostriana.dam.dto;

import java.time.LocalTime;

import com.salesianostriana.dam.dto.ListaEstablecimientoDto.ListaEstablecimientoDtoBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class GerenteDto2 {
	
	private long id;
	private String email;
	private String nombre;
	private String apellidos;
	private String tlfContacto;
	
}
