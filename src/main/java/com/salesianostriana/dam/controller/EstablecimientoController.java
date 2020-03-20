package com.salesianostriana.dam.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salesianostriana.dam.conversor.ConversorEstablecimiento;
import com.salesianostriana.dam.dto.ListaEstablecimientoDto;
import com.salesianostriana.dam.model.Establecimiento;
import com.salesianostriana.dam.service.CategoriaService;
import com.salesianostriana.dam.service.EstablecimientoService;

@RestController
@RequestMapping("/api")
public class EstablecimientoController {
	@Autowired
	private EstablecimientoService service;
	@Autowired
	private ConversorEstablecimiento converter;
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping("/local/")
	public List<ListaEstablecimientoDto> buscarLocal() {
		List<Establecimiento> locales = service.findAll();
		List<ListaEstablecimientoDto> localListDto = new ArrayList<>();
		
		for(Establecimiento e : locales) {
			localListDto.add(converter.establecimientoFilterDto(e));
		}
		
		return localListDto;
		
	}

}
