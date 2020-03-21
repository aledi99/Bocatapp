package com.salesianostriana.dam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.salesianostriana.dam.dto.CreateClienteDto;
import com.salesianostriana.dam.files.FileSystemStorageService;
import com.salesianostriana.dam.model.Avatar;
import com.salesianostriana.dam.model.Cliente;
import com.salesianostriana.dam.model.Usuario;
import com.salesianostriana.dam.service.AvatarService;
import com.salesianostriana.dam.service.ClienteService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClienteController {
	
	private final ClienteService clienteService;
	private final FileSystemStorageService fileStorageService;
	private final AvatarService avatarService;

	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestParam("file") MultipartFile file, @RequestParam("email") String email, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("nombre") String nombre, @RequestParam("apellidos") String apellidos, @RequestParam("edad") int edad) {
		String filename = fileStorageService.storeFile(file);
		
		CreateClienteDto createClienteDto = new CreateClienteDto(email, username, password, nombre, apellidos, edad);
		
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/downloadFile/")
				.path(filename)
				.toUriString();
		
		Avatar avatar = avatarService.save(Avatar.builder()
											.nombreFichero(filename)
											.uriDescargaFichero(fileDownloadUri)
											.tipoFichero(file.getContentType())
											.tamanyo(file.getSize())
											.build());
		
		
		Cliente user = clienteService.newCliente(createClienteDto, avatar);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	

	@GetMapping("/findByEmail")
	public Usuario findByEmail(@RequestParam String email) {
		
		return clienteService.findFirstByEmail(email);   
        
        
    }


}
