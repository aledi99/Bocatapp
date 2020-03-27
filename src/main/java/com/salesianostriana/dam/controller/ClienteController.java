package com.salesianostriana.dam.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.salesianostriana.dam.conversor.ConversorEstablecimiento;
import com.salesianostriana.dam.conversor.ConversorUsuario;
import com.salesianostriana.dam.dto.CreateClienteDto;
import com.salesianostriana.dam.dto.EditAvatarDto;
import com.salesianostriana.dam.dto.EditImagenProductoDto;
import com.salesianostriana.dam.dto.ListUsersDto;
import com.salesianostriana.dam.dto.ProductoDto2;
import com.salesianostriana.dam.files.FileSystemStorageService;
import com.salesianostriana.dam.model.Avatar;
import com.salesianostriana.dam.model.Cliente;
import com.salesianostriana.dam.model.Establecimiento;
import com.salesianostriana.dam.model.Gerente;
import com.salesianostriana.dam.model.Producto;
import com.salesianostriana.dam.model.Usuario;
import com.salesianostriana.dam.service.AvatarService;
import com.salesianostriana.dam.service.ClienteService;

import ch.qos.logback.core.pattern.Converter;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClienteController {
	
	private final ClienteService clienteService;
	private final FileSystemStorageService fileStorageService;
	private final AvatarService avatarService;
	private final ConversorUsuario converter;	

	
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
	
	
	/*@GetMapping("/me/")
	public ResponseEntity<?> getDatosCliente(OAuth2Authentication oAuth) {

		String principal = oAuth.getUserAuthentication().getPrincipal().toString();

		if (clienteService.findFirstByEmail(principal) != null) {
			Cliente cliente = clienteService.findFirstByEmail(principal);
			

			

			return ResponseEntity.ok().body(productoList);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay establecimiento con ese Id.");
		}

	}*/
	
	
	
	
	
	@GetMapping("/list")
	public List<ListUsersDto> listarClientes() {
		List<Cliente> users = clienteService.findAll();
		List<ListUsersDto> listClienteDto = new ArrayList<>();
		
		for(Cliente c : users) {
			listClienteDto.add(converter.converterListaClientesDto(c));
		}
		
		return listClienteDto;
		
		
		
	}
	
	
	@PutMapping("client/{id}/editPhoto")
	public ResponseEntity<?> editarFotoCliente(@PathVariable Optional<Long> id,@RequestBody EditAvatarDto editAvatarDto){
		Long theId = id.orElse(-1L);
		
		if(clienteService.findById(theId)!=null) {
			
			Cliente cliente = clienteService.findById(theId);
			
			cliente.setAvatar(editAvatarDto.getAvatar());
			
			
			clienteService.edit(cliente);
			
			return new ResponseEntity<>(cliente, HttpStatus.CREATED);
			
		}else {
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay un cliente con este id.");
				
			}


		
	}


}
