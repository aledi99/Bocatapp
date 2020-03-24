package com.salesianostriana.dam.dto;

import java.time.LocalTime;

import com.salesianostriana.dam.dto.EstablecimientoDto.EstablecimientoDtoBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ImagenDto {
	
	private Long id;
	
	private String nombreFichero;
	private String uriDescargaFichero;
	private String tipoFichero;
	private long tamanyo;

}
