package com.salesianostriana.dam.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.salesianostriana.dam.conversor.ConversorUsuario;
import com.salesianostriana.dam.dto.CreateGerenteDto;
import com.salesianostriana.dam.dto.ListUsersDto;
import com.salesianostriana.dam.files.FileSystemStorageService;
import com.salesianostriana.dam.model.Avatar;
import com.salesianostriana.dam.model.Cliente;
import com.salesianostriana.dam.model.Gerente;
import com.salesianostriana.dam.service.AvatarService;
import com.salesianostriana.dam.service.GerenteService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/gerente")
@RequiredArgsConstructor
public class GerenteController {
	
	private final GerenteService gerenteService;
	private final FileSystemStorageService fileStorageService;
	private final AvatarService avatarService;
	private final ConversorUsuario converter; 
	
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestParam("file") MultipartFile file, @RequestParam("email") String email, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("nombre") String nombre, @RequestParam("apellidos") String apellidos, @RequestParam("edad") int edad, @RequestParam("tlfContacto") String tlfContacto) {
		String filename = fileStorageService.storeFile(file);
		
		CreateGerenteDto createGerenteDto = new CreateGerenteDto(email, username, password, nombre, apellidos, tlfContacto, edad);
		
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
		
		
		Gerente user = gerenteService.newGerente(createGerenteDto, avatar);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
	@GetMapping("/list")
	public List<ListUsersDto> listarGerentes() {
		List<Gerente> users = gerenteService.findAll();
		List<ListUsersDto> listClienteDto = new ArrayList<>();
		
		for(Gerente g : users) {
			listClienteDto.add(converter.converterListaGerentesDto(g));
		}
		
		return listClienteDto;
		
		
		
	}
	


	

}
