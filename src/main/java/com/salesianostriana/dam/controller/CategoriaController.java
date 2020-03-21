package com.salesianostriana.dam.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salesianostriana.dam.conversor.ConversorCategoria;
import com.salesianostriana.dam.model.Establecimiento;
import com.salesianostriana.dam.service.EstablecimientoService;

@RestController
@RequestMapping("/api")
public class CategoriaController {
	@Autowired
	private EstablecimientoService service;
	@Autowired
	private ConversorCategoria converter;
	
	@GetMapping("/categorias/")
	public List<String> listarCatActivas() {
		List<Establecimiento> locales = new ArrayList<>();
		List<String> categorias = new ArrayList<>();
		
		locales = service.findAll();
		
		for(int i = 0; i < locales.size(); i++) {
			categorias.add(locales.get(i).getCategoria().getNombre());
		}
		
		categorias.stream().distinct().collect(Collectors.toList());
		
		return categorias;
	}

}
