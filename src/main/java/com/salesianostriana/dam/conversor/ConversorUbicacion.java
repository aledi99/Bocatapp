package com.salesianostriana.dam.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesianostriana.dam.dto.UbicacionDto;
import com.salesianostriana.dam.model.Ubicacion;
import com.salesianostriana.dam.service.UbicacionService;

@Component
public class ConversorUbicacion {
	
	private static UbicacionService service;
	
	@Autowired
	public void setServicios(UbicacionService service) {
		ConversorUbicacion.service = service;
	}
	
	UbicacionDto ubicacionToUbicacionDto(Ubicacion ubicacion) {
		return UbicacionDto.builder()
				.latitud(ubicacion.getLatitud())
				.longitud(ubicacion.getLongitud())
				.build();
	}

}
