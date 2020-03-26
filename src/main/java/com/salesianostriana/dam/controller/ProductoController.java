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

import com.salesianostriana.dam.conversor.ConversorProducto;
import com.salesianostriana.dam.dto.CreateProductoDto;
import com.salesianostriana.dam.dto.EditImagenEstablecimientoDto;
import com.salesianostriana.dam.dto.EditImagenProductoDto;
import com.salesianostriana.dam.dto.EditProductoDto;
import com.salesianostriana.dam.dto.ProductoDto2;
import com.salesianostriana.dam.files.FileSystemStorageService;
import com.salesianostriana.dam.model.Gerente;
import com.salesianostriana.dam.model.Imagen;
import com.salesianostriana.dam.model.Producto;
import com.salesianostriana.dam.service.EstablecimientoService;
import com.salesianostriana.dam.service.GerenteService;
import com.salesianostriana.dam.service.ImagenService;
import com.salesianostriana.dam.service.ProductoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductoController {

	@Autowired
	private EstablecimientoService estService;

	@Autowired
	private ConversorProducto converter;

	@Autowired
	private FileSystemStorageService fileStorageService;

	@Autowired
	private ProductoService service;

	@Autowired
	private ImagenService imagenService;

	@Autowired
	private GerenteService gerService;

	@GetMapping("/local/me/productos/")
	public ResponseEntity<?> getProductosGerente(OAuth2Authentication oAuth) {

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

	}

	@PostMapping("/producto/")
	public ResponseEntity<?> newProducto(@RequestParam("file") MultipartFile file,
			@RequestParam("nombre") String nombre, @RequestParam("descripcion") String descripcion,
			@RequestParam("precio") double precio, @RequestParam("glucosa") boolean glucosa,
			@RequestParam("lactosa") boolean lactosa, OAuth2Authentication oauth) {
		String filename = fileStorageService.storeFile(file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(filename).toUriString();

		Imagen imagen = imagenService.save(Imagen.builder().nombreFichero(filename).uriDescargaFichero(fileDownloadUri)
				.tipoFichero(file.getContentType()).tamanyo(file.getSize()).build());

		CreateProductoDto productoDto = new CreateProductoDto(nombre, descripcion, precio, glucosa, lactosa);
		Producto producto = service.newProducto(productoDto, oauth, imagen);
		ProductoDto2 productoDto2 = converter.productoToProductoDto2(producto);
		return new ResponseEntity<>(productoDto2, HttpStatus.CREATED);
	}

	@DeleteMapping("/producto/{id}")
	public ResponseEntity<?> deleteProducto(@PathVariable Optional<Long> id) {
		Long theId = id.orElse(-1L);

		if (service.findById(theId) != null) {
			Producto producto = service.findById(theId);
			service.delete(producto);

			return ResponseEntity.noContent().build();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay un producto con este id.");
		}

	}

	@PutMapping("/producto/{id}")
	public ResponseEntity<?> editProducto(@PathVariable Optional<Long> id, @RequestBody EditProductoDto editDto) {
		Long theId = id.orElse(-1L);

		if (service.findById(theId) != null) {
			Producto producto = service.findById(theId);

			producto.setNombre(editDto.getNombre());
			producto.setDescripcion(editDto.getDescripcion());
			producto.setPrecio(editDto.getPrecio());
			producto.setLactosa(editDto.isLactosa());
			producto.setGluten(editDto.isGlucosa());

			service.edit(producto);

			return new ResponseEntity<>(producto, HttpStatus.CREATED);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay un producto con este id.");
		}
	}
	
	@PutMapping("producto/{id}/editPhoto")
	public ResponseEntity<?> editarFotoProducto(@PathVariable Optional<Long> id,@RequestBody EditImagenProductoDto editImagenProductoDto){
		Long theId = id.orElse(-1L);
		
		if(service.findById(theId)!=null) {
			
			Producto producto = service.findById(theId);
			
			producto.setImagen(editImagenProductoDto.getImagen());
			
			service.edit(producto);
			
			return new ResponseEntity<>(producto, HttpStatus.CREATED);
			
		}else {
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay un producto con este id.");
				
			}


		
	}

}
