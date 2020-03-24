package com.salesianostriana.dam.dto;

import java.time.LocalTime;

import com.salesianostriana.dam.dto.ClienteDto.ClienteDtoBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CreateEstablecimientoDto {
	
	private String nombre;
	private String descripción;
	private double presupuesto;
	private LocalTime horaApertura,horaCierre;
	


}
