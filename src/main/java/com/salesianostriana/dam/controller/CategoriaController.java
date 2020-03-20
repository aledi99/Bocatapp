package com.salesianostriana.dam.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.salesianostriana.dam.conversor.ConversorCategoria;
import com.salesianostriana.dam.conversor.ConversorEstablecimiento;
import com.salesianostriana.dam.dto.CategoriaDtoName;
import com.salesianostriana.dam.dto.CreateCategoriaDto;
import com.salesianostriana.dam.model.Categoria;
import com.salesianostriana.dam.model.Establecimiento;
import com.salesianostriana.dam.model.Producto;
import com.salesianostriana.dam.service.CategoriaService;
import com.salesianostriana.dam.service.EstablecimientoService;

@RestController
@RequestMapping("/api")
public class CategoriaController {
	@Autowired
	private EstablecimientoService service;
	@Autowired
	private ConversorCategoria converter;
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping("/categorias")
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
	
	@GetMapping("/categoria/{id}")
	public Categoria unaCategoria(@PathVariable Optional<Long> id) {
		Long idd = id.orElse(-1L);
		
		return categoriaService.findById(idd);
	}
	
	@PostMapping("categoria/")
	public ResponseEntity<?> nuevaCategoria(@RequestBody CreateCategoriaDto createCategoriaDto) {
		Categoria p = categoriaService.save(converter.convertCategoriaDtotoCategoria(createCategoriaDto));
		return new ResponseEntity<Categoria>(p, HttpStatus.CREATED);
	}
	
	/*@PutMapping("categoria/{id}")
	public ResponseEntity<?> editarCategoria (@PathVariable Optional<Long> id, @RequestBody Categoria categoria) {
		Long theId = id.orElse(-1L);
		return categoriaService.findById(theId).map(e -> {			
			p.setNombre(producto.getNombre());
			
			
			
					
			return ResponseEntity.ok(service.save(p));
		}).orElse(ResponseEntity.notFound().build());
	}*/
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategoria(@PathVariable Optional<Long> id) {
		Long theId = id.orElse(-1L);
		Establecimiento e = service.findById(theId);
		
		if(e==null) {
			return ResponseEntity.notFound().build();
		}else {
			service.delete(e);
			return ResponseEntity.noContent().build();
		}
		
	}


}
