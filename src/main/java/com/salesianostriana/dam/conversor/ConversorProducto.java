package com.salesianostriana.dam.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.salesianostriana.dam.dto.ListaProductosDto;
import com.salesianostriana.dam.dto.ProductoDto;
import com.salesianostriana.dam.model.Producto;
import com.salesianostriana.dam.service.ProductoService;

@Component
public class ConversorProducto {
	
	/*private static ProductoService productoService;
	
	@Autowired
	public void setServicios(ProductoService productoService) {
		ConversorProducto.productoService = productoService;

	}*/
	
	
	public ListaProductosDto converterListaProductoDto(Producto producto) {
		
		
		return ListaProductosDto.builder()
				.id(producto.getId())
				.nombre(producto.getNombre())
				.precio(String.valueOf(producto.getPrecio()))
				.build();
		

		
	}
	

	
	

	private static ProductoService service;
	
	@Autowired
	public void setServicios(ProductoService service) {
		ConversorProducto.service = service;
	}
	
	public ProductoDto productoToProductoDto(Producto producto) {
		
		return ProductoDto.builder()
				.id(producto.getId())
				.nombre(producto.getNombre())
				.descripcion(producto.getDescripcion())
				.activo(producto.isActivo())
				.glucosa(producto.isGluten())
				.lactosa(producto.isLactosa())
				.build();
		
	}

}
