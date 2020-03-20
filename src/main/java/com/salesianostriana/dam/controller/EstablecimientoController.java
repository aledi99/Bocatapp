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

import com.salesianostriana.dam.conversor.ConversorEstablecimiento;
import com.salesianostriana.dam.dto.CreateEstablecimientoDto;
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
	
	@GetMapping("/local/{id}")
	public Establecimiento unEstablecimiento(@PathVariable Optional<Long> id) {
		Long idd = id.orElse(-1L);
		
		return service.findById(idd);
	}
	
	@PostMapping("local/")
	public ResponseEntity<?> nuevoEstablecimiento(@RequestBody CreateEstablecimientoDto createEstablecimientoDto) {
		Establecimiento e = service.save(converter.convertEstablecimientoDtoToEstablecimiento(createEstablecimientoDto));
		return new ResponseEntity<Establecimiento>(e, HttpStatus.CREATED);
	}
	
	/*@PutMapping("local/{id}")
	public ResponseEntity<?> editarEstablecimiento (@PathVariable Optional<Long> id, @RequestBody Establecimiento establecimiento) {
		Long theId = id.orElse(-1L);
		return service.findById(theId).map(e -> {			
			e.setNombre(establecimiento.getNombre());
			
			
					
			return ResponseEntity.ok(service.save(e));
		}).orElse(ResponseEntity.notFound().build());
	}*/
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEstablecimiento(@PathVariable Optional<Long> id) {
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
