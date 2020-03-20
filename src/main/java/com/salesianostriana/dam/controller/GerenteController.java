package com.salesianostriana.dam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.salesianostriana.dam.dto.CreateGerenteDto;
import com.salesianostriana.dam.model.Gerente;
import com.salesianostriana.dam.service.GerenteService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/gerente")
@RequiredArgsConstructor
public class GerenteController {
	
	private final GerenteService gerenteService;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody CreateGerenteDto createGerenteDto) {
		
		Gerente user = gerenteService.newGerente(createGerenteDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	

	

}
