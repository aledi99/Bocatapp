package com.salesianostriana.dam.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.salesianostriana.dam.dto.CreateClienteDto;
import com.salesianostriana.dam.dto.CreateUserDto;
import com.salesianostriana.dam.dto.converter.UserDtoConverter;
import com.salesianostriana.dam.model.Cliente;
import com.salesianostriana.dam.model.Usuario;
import com.salesianostriana.dam.service.ClienteService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClienteController {
	
	private final ClienteService clienteService;

	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody CreateClienteDto createClienteDto) {
		
		Cliente user = clienteService.newCliente(createClienteDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	

	@GetMapping("/findByEmail")
	public Usuario findByEmail(@RequestParam String email) {
		
		return clienteService.findFirstByEmail(email);   
        
        
    }


}
