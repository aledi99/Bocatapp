package com.salesianostriana.dam.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.salesianostriana.dam.dto.CreateAdminDto;
import com.salesianostriana.dam.dto.ProductoDto2;
import com.salesianostriana.dam.files.FileSystemStorageService;
import com.salesianostriana.dam.model.Admin;
import com.salesianostriana.dam.model.Avatar;
import com.salesianostriana.dam.model.Establecimiento;
import com.salesianostriana.dam.model.Gerente;
import com.salesianostriana.dam.model.Role;
import com.salesianostriana.dam.service.AdminService;
import com.salesianostriana.dam.service.AvatarService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
	
	private final AdminService adminService;
	private final FileSystemStorageService fileStorageService;
	private final AvatarService avatarService;
	
	String fecha = "11/06/1999";
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestParam("file") MultipartFile file, @RequestParam("email") String email, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("nombre") String nombre, @RequestParam("apellidos") String apellidos, @RequestParam("edad") int edad) {
		String filename = fileStorageService.storeFile(file);
		
		CreateAdminDto createAdminDto = new CreateAdminDto(email, username, password, nombre, apellidos, edad);
		
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
		
		
		Admin user = adminService.newAdmin(createAdminDto, avatar);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
	
	/*@GetMapping("/local/me/productos/")
	public ResponseEntity<?> (OAuth2Authentication oAuth) {

		String principal = oAuth.getUserAuthentication().getPrincipal().toString();

		if (gerService.findFirstByEmail(principal) != null) {
			Gerente gerente = gerService.findFirstByEmail(principal);
			List<ProductoDto2> productoList = new ArrayList<>();

			if (gerente.getEstablecimiento().getProducto() != null) {
				for (int i = 0; i < gerente.getEstablecimiento().getProducto().size(); i++) {
					productoList
							.add(converter.productoToProductoDto2(gerente.getEstablecimiento().getProducto().get(i)));

				}
			}

			return ResponseEntity.ok().body(productoList);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay establecimiento con ese Id.");
		}

	}*/
	
	
	
	@GetMapping("/{username}")
	public ResponseEntity<?> getRole(@PathVariable String username) {
		String rolAdmin = null;
		
		if(adminService.findFirstByEmail(username) != null) {
			Admin admin = adminService.findFirstByEmail(username);
		
		for(Role rol : admin.getRoles()) {
			rolAdmin = rol.name();
		}
		
		return ResponseEntity.ok().body(rolAdmin);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay un producto con este id.");
		}

	}
	

	
	
	

	

}
