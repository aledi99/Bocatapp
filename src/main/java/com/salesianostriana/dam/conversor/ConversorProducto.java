package com.salesianostriana.dam.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesianostriana.dam.dto.ListaProductosDto;
import com.salesianostriana.dam.model.Producto;
import com.salesianostriana.dam.service.ProductoService;

@Component
public class ConversorProducto {
	
	private static ProductoService productoService;
	
	@Autowired
	public void setServicios(ProductoService productoService) {
		ConversorProducto.productoService = productoService;

	}
	
	
	public ListaProductosDto converterListaProductoDto(Producto producto) {
		
		
		return ListaProductosDto.builder()
				.id(producto.getId())
				.nombre(producto.getNombre())
				.precio(String.valueOf(producto.getPrecio()))
				.build();
		

		
	}
	

	
	


}
