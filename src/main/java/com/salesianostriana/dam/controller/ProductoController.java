package com.salesianostriana.dam.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salesianostriana.dam.conversor.ConversorProducto;
import com.salesianostriana.dam.dto.ListaProductosDto;
import com.salesianostriana.dam.model.Producto;
import com.salesianostriana.dam.service.ProductoService;

@RestController
@RequestMapping("/api")
public class ProductoController {
	
	@Autowired
	private ProductoService service;
	@Autowired
	private ConversorProducto converter;
	@Autowired
	private ListaProductosDto listaProductosDto;

	
	@GetMapping("/productos/")
	public List<ListaProductosDto> buscarProducto() {
		List<Producto> productos = service.findAll();
		List<ListaProductosDto> localListDto = new ArrayList<>();
		
		for(Producto p : productos) {
			localListDto.add(converter.converterListaProductoDto(p));
		}
		
		return localListDto;
		
	}
	
	


}
