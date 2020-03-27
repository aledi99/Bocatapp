package com.salesianostriana.dam.dto;

import com.salesianostriana.dam.model.Imagen;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class EditImagenEstablecimientoDto {
	
	private Imagen imagen;

}
