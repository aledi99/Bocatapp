package com.salesianostriana.dam.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesianostriana.dam.dto.GerenteDto2;
import com.salesianostriana.dam.model.Gerente;
import com.salesianostriana.dam.service.GerenteService;

@Component
public class ConversorGerente {
	
	private static GerenteService service;
	
	@Autowired
	public void setServicios(GerenteService gerenteService) {

		ConversorGerente.service = gerenteService;

	}
	
	public GerenteDto2 gerenteToGerenteDto(Gerente gerente) {
		return GerenteDto2.builder()
				.id(gerente.getId())
				.nombre(gerente.getNombre())
				.apellidos(gerente.getApellidos())
				.email(gerente.getEmail())
				.tlfContacto(gerente.getTlfContacto())
				.build();
	}

}
