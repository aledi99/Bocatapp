package com.salesianostriana.dam.dto;

import java.time.LocalTime;

import com.salesianostriana.dam.model.Categoria;
import com.salesianostriana.dam.model.Imagen;
import com.salesianostriana.dam.model.Ubicacion;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class EditEstablecimientoDto {
	
	private String nombre,descripcion;
	private LocalTime horaApertura,horaCierre;
	private Ubicacion localizacion;
	private Categoria categoria;
	private Imagen imagen;

}
