package com.salesianostriana.dam.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.salesianostriana.dam.dto.CreateProductoDto;
import com.salesianostriana.dam.dto.EditProductoDto;
import com.salesianostriana.dam.model.Producto;
import com.salesianostriana.dam.service.ProductoService;

@RestController
@RequestMapping("/api/local/maintainer")
public class ProductoController {
	
	@Autowired
	private ProductoService service;
	
	@PostMapping("/producto/")
	public ResponseEntity<?> newProducto(@RequestBody CreateProductoDto createDto, OAuth2Authentication oauth) {
		Producto producto = service.newProducto(createDto);
		return new ResponseEntity<>(producto, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/producto/{id}")
	public ResponseEntity<?> deleteProducto(@PathVariable Optional<Long> id) {
		Long theId = id.orElse(-1L);
		
		if(service.findById(theId) != null) {
			Producto producto = service.findById(theId);
			service.delete(producto);
			
			return ResponseEntity.noContent().build();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay un producto con este id.");
		}
		
	}
	
	@PutMapping("/producto/{id}")
	public ResponseEntity<?> editProducto(@PathVariable Optional<Long> id, @RequestBody EditProductoDto editDto ) {
		Long theId = id.orElse(-1L);
		
		if(service.findById(theId) != null) {
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

}
