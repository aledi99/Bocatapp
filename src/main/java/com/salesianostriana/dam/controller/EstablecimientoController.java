package com.salesianostriana.dam.controller;

import java.time.LocalTime;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.salesianostriana.dam.conversor.ConversorEstablecimiento;
import com.salesianostriana.dam.dto.CreateEstablecimientoDto;
import com.salesianostriana.dam.dto.EditEstablecimientoDto;
import com.salesianostriana.dam.dto.ListaEstablecimientoDto;
import com.salesianostriana.dam.files.FileSystemStorageService;
import com.salesianostriana.dam.model.Establecimiento;
import com.salesianostriana.dam.model.Imagen;
import com.salesianostriana.dam.service.AdminService;
import com.salesianostriana.dam.service.AvatarService;
import com.salesianostriana.dam.service.CategoriaService;
import com.salesianostriana.dam.service.EstablecimientoService;
import com.salesianostriana.dam.service.ImagenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EstablecimientoController {
	@Autowired
	private EstablecimientoService service;
	@Autowired
	private ConversorEstablecimiento converter;	
	private final FileSystemStorageService fileStorageService;	
	private final ImagenService imagenService;	
	
	
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
	
	@PostMapping("/local/{id}")
	public Establecimiento añadirLocalAFavs(@PathVariable Optional<Long> id){
		Long idd = id.orElse(-1L);
		
		
		return service.findById(idd);
		
		
	}
	
	@GetMapping("local/fav/")
	public List<CreateEstablecimientoDto> listarLocalesFavs(){
		List<Establecimiento> locales = service.findAllFavs();
		List<CreateEstablecimientoDto> favListDto = new ArrayList<>();
		
		for (Establecimiento e : locales) {
			favListDto.add(converter.convertEstablecimientotoEstablecimientoDto(e));
			
		}
		
		return favListDto;
		
	}
	

	
	@PostMapping("local/")
	public ResponseEntity<?> nuevoEstablecimiento(@RequestParam("file") MultipartFile file,@RequestParam("nombre") String nombre, @RequestParam("descripcion") String descripcion, @RequestParam("presupuesto") double presupuesto, @RequestParam("horaApertura") LocalTime horaApertura, @RequestParam("horaCierre") LocalTime horaCierre) {
		String filename = fileStorageService.storeFile(file);
		
		CreateEstablecimientoDto createEstablecimientoDto = new CreateEstablecimientoDto(nombre,descripcion,presupuesto,horaApertura,horaCierre);
		
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/downloadFile/")
				.path(filename)
				.toUriString();
		
		Imagen imagen = imagenService.save(Imagen.builder()
											.nombreFichero(filename)
											.uriDescargaFichero(fileDownloadUri)
											.tipoFichero(file.getContentType())
											.tamanyo(file.getSize())
											.build());
		
		 
	
		Establecimiento e = service.newEstablecimiento(createEstablecimientoDto, imagen);
		return new ResponseEntity<Establecimiento>(e, HttpStatus.CREATED);
	}
	
	@PutMapping("local/{id}")
	public ResponseEntity<?> editarEstablecimiento (@PathVariable Optional<Long> id, @RequestBody EditEstablecimientoDto editestablecimientoDto) {
		Long theId = id.orElse(-1L);
		
		if(service.findById(theId)!=null) {
			Establecimiento establecimiento = service.findById(theId);
			
			establecimiento.setNombre( editestablecimientoDto.getNombre());
			establecimiento.setDescripcion(editestablecimientoDto.getDescripcion());
			establecimiento.setHoraApertura(editestablecimientoDto.getHoraApertura());
			establecimiento.setHoraCierre(editestablecimientoDto.getHoraCierre());
			establecimiento.setLocalizacion(editestablecimientoDto.getLocalizacion());
			establecimiento.setCategoria(editestablecimientoDto.getCategoria());
			establecimiento.setImagen(editestablecimientoDto.getImagen());
			
			service.edit(establecimiento);
			
			return new ResponseEntity<>(establecimiento, HttpStatus.CREATED);			 

			
		}else {
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay un establecimiento con este id.");
		}
		
		
	}
	
	
	@DeleteMapping("establecimiento/{id}")
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
