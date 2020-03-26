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
import org.springframework.web.server.ResponseStatusException;

import com.salesianostriana.dam.conversor.ConversorCategoria;
import com.salesianostriana.dam.conversor.ConversorEstablecimiento;
import com.salesianostriana.dam.dto.CategoriaDto;
import com.salesianostriana.dam.dto.CategoriaDto2;
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
	
	@GetMapping("/categorias/active")
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
	
	@GetMapping("/categorias/name")
	public ResponseEntity<?> getCategoriasName() {
		List<Categoria> categorias = categoriaService.findAll();
		List<CategoriaDto2> categoriasName = new ArrayList<>();
		
		for(int i = 0; i < categorias.size(); i++) {
			categoriasName.add(converter.categoriaToCategoriaDto2(categorias.get(i)));
		}
		
		return ResponseEntity.ok().body(categoriasName);
	}
	
	@GetMapping("/categoria/{id}")
	public Categoria unaCategoria(@PathVariable Optional<Long> id) {
		Long idd = id.orElse(-1L);
		
		return categoriaService.findById(idd);
	}
	
	@PostMapping("categoria/")
	public ResponseEntity<?> nuevaCategoria(@RequestBody CreateCategoriaDto createCategoriaDto) {
		Categoria c = categoriaService.save(converter.convertCategoriaDtotoCategoria(createCategoriaDto));
		return new ResponseEntity<Categoria>(c, HttpStatus.CREATED);
	}
	
	@PutMapping("categoria/{id}")
	public ResponseEntity<?> editarCategoria (@PathVariable Optional<Long> id, @RequestBody CategoriaDto categoriadto) {
		Long theId = id.orElse(-1L);
		 if(categoriaService.findById(theId)!=null) {
			 
			 Categoria categoria = categoriaService.findById(theId);			 
			 categoria.setNombre(categoriadto.getNombre());
			 
			 return new ResponseEntity<>(categoria, HttpStatus.CREATED);			
			 
		 }else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe esta categoría");
		 }
		
	
		
	}
	
	
	@DeleteMapping("categoria/{id}")
	public ResponseEntity<?> deleteCategoria(@PathVariable Optional<Long> id) {
		Long theId = id.orElse(-1L);
		Categoria c = categoriaService.findById(theId);
		
		if(c==null) {
			return ResponseEntity.notFound().build();
		}else {
			categoriaService.delete(c);
			return ResponseEntity.noContent().build();
		}
		
	}


}
