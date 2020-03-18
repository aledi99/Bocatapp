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

import com.salesianostriana.dam.dto.CreateUserDto;
import com.salesianostriana.dam.dto.converter.UserDtoConverter;
import com.salesianostriana.dam.model.Usuario;
import com.salesianostriana.dam.service.UsuarioService;

import lombok.RequiredArgsConstructor;

/**
 * Este Controller contiene todos los métodos que se usan para manejar la gestión de los usuarios
 * @author Alberto
 *
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	
	private final UsuarioService usuarioService;
	private final UserDtoConverter userDtoConverter;

	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody CreateUserDto createUserDto) {
		
		Usuario user = usuarioService.newUser(createUserDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(userDtoConverter.convertUserEntityToUserDto(user));
	}
	

	@GetMapping("/findByEmail")
	public Usuario findByEmail(@RequestParam String email) {
		
		return usuarioService.findFirstByEmail(email);   
        
        
    }


}
