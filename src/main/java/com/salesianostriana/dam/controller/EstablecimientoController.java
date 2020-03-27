package com.salesianostriana.dam.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
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
import com.salesianostriana.dam.dto.CreateLocalizacionDto;
import com.salesianostriana.dam.dto.EditEstablecimientoDto;
import com.salesianostriana.dam.dto.EditImagenEstablecimientoDto;
import com.salesianostriana.dam.dto.ListaEstablecimientoDto;
import com.salesianostriana.dam.dto.ProductoDto2;
import com.salesianostriana.dam.files.FileSystemStorageService;
import com.salesianostriana.dam.model.Categoria;
import com.salesianostriana.dam.model.Establecimiento;
import com.salesianostriana.dam.model.Gerente;
import com.salesianostriana.dam.model.Imagen;
import com.salesianostriana.dam.model.Ubicacion;
import com.salesianostriana.dam.service.AdminService;
import com.salesianostriana.dam.service.AvatarService;
import com.salesianostriana.dam.service.CategoriaService;
import com.salesianostriana.dam.service.EstablecimientoService;
import com.salesianostriana.dam.service.GerenteService;
import com.salesianostriana.dam.service.ImagenService;
import com.salesianostriana.dam.service.UbicacionService;

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
	private final CategoriaService catService;
	
	private final UbicacionService ubService;
	
	@Autowired
	private GerenteService gerService;
	
	@GetMapping("/local/me/")
	public ResponseEntity<?> getProductosGerente(OAuth2Authentication oAuth) {
		ListaEstablecimientoDto establecimiento;
		String principal = oAuth.getUserAuthentication().getPrincipal().toString();

		if (gerService.findFirstByEmail(principal) != null) {
			Gerente gerente = gerService.findFirstByEmail(principal);
			establecimiento = converter.establecimientoFilterDto(gerente.getEstablecimiento());

			return ResponseEntity.ok().body(establecimiento);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay establecimiento con ese Id.");
		}

	}
	
	
	@GetMapping("/local/")
	public ResponseEntity<?> buscarLocal() {
		List<Establecimiento> locales = service.findAll();
		List<ListaEstablecimientoDto> localListDto = new ArrayList<>();
		
		for(Establecimiento e : locales) {
			localListDto.add(converter.establecimientoFilterDto(e));
		}
		
		return ResponseEntity.ok().body(localListDto);
		
	}
	
	@GetMapping("/local/{id}")
	public ResponseEntity<?> unEstablecimiento(@PathVariable Optional<Long> id) {
		Long idd = id.orElse(-1L);
		ListaEstablecimientoDto estDto;
		
		if(service.findById(idd) != null) {
			Establecimiento establecimiento = service.findById(idd);
			estDto = converter.establecimientoFilterDto(establecimiento);
			
			return ResponseEntity.ok().body(estDto);
		}
		
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay un establecimiento con este id.");
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
	public ResponseEntity<?> nuevoEstablecimiento(@RequestParam("file") MultipartFile file,@RequestParam("nombre") String nombre, @RequestParam("descripcion") String descripcion, @RequestParam("presupuesto") double presupuesto, @RequestParam("horaApertura") String horaApertura, @RequestParam("horaCierre") String horaCierre, @RequestParam("latitud") double latitud , @RequestParam("longitud") double longitud,@RequestParam("nombreCategoria") String nombreCategoria, OAuth2Authentication oAuth) {

		String filename = fileStorageService.storeFile(file);
		
		CreateLocalizacionDto createLocalizacionDto = new CreateLocalizacionDto(latitud,longitud);
		
		CreateEstablecimientoDto createEstablecimientoDto = new CreateEstablecimientoDto(nombre,descripcion,presupuesto,horaApertura,horaCierre,createLocalizacionDto);
		
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
		
		Ubicacion ubicacion = ubService.save(Ubicacion.builder()
				.latitud(latitud)
				.longitud(longitud)
				.build());
		
		String principal = oAuth.getUserAuthentication().getPrincipal().toString();
		
		if (gerService.findFirstByEmail(principal) != null) {
			Gerente gerente = gerService.findFirstByEmail(principal);

			if (gerente.getEstablecimiento() == null) {	
				
				Categoria categoria = catService.buscarPorNombre(nombreCategoria);
				
				Establecimiento e = service.newEstablecimiento(createEstablecimientoDto, imagen, ubicacion, gerente, categoria);
				gerente.setEstablecimiento(e);
				gerService.edit(gerente);
				
				return new ResponseEntity<Establecimiento>(e, HttpStatus.CREATED);	
				
			}else {
				return ResponseEntity.badRequest().build();
			}
			
		} else {
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay establecimiento con ese Id.");
			
		}
			
		
		 		
	}
	
	@PutMapping("local/{id}")
	public ResponseEntity<?> editarEstablecimiento (@PathVariable Optional<Long> id, @RequestBody EditEstablecimientoDto editestablecimientoDto) {
		Long theId = id.orElse(-1L);
		
		if(service.findById(theId)!=null) {
			
			Establecimiento establecimiento = service.findById(theId);
			
			Ubicacion ubicacion = ubService.findById(establecimiento.getLocalizacion().getId());
			
			establecimiento.setNombre( editestablecimientoDto.getNombre());
			establecimiento.setDescripcion(editestablecimientoDto.getDescripcion());
			establecimiento.setHoraApertura(editestablecimientoDto.getHoraApertura());
			establecimiento.setAbierto(editestablecimientoDto.isAbierto());
			establecimiento.setPresupuesto(editestablecimientoDto.getPresupuesto());
			establecimiento.setHoraCierre(editestablecimientoDto.getHoraCierre());
			establecimiento.setCategoria(catService.buscarPorNombre(editestablecimientoDto.getNombreCategoria()));
			
			ubicacion.setLatitud(editestablecimientoDto.getLatitud());
			ubicacion.setLongitud(editestablecimientoDto.getLongitud());
			
			service.edit(establecimiento);
			ubService.edit(ubicacion);
			
			return ResponseEntity.accepted().build();		 

			
		}else {
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay un establecimiento con este id.");
		}
		
		
	}
	
	@PutMapping("local/{id}/editPhoto")
	public ResponseEntity<?> editarFotoEstablecimiento(@PathVariable Optional<Long> id,@RequestBody EditImagenEstablecimientoDto editImagenEstablecimientoDto){
		Long theId = id.orElse(-1L);
		
		if(service.findById(theId)!=null) {
			
			Establecimiento establecimiento = service.findById(theId);
			
			establecimiento.setImagen(editImagenEstablecimientoDto.getImagen());
			
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
