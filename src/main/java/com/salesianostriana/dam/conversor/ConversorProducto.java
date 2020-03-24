package com.salesianostriana.dam.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.salesianostriana.dam.dto.EstablecimientoDto;
import com.salesianostriana.dam.dto.ImagenDto;


import com.salesianostriana.dam.dto.ListaProductosDto;
import com.salesianostriana.dam.dto.ProductoDto;
import com.salesianostriana.dam.dto.ProductoDto2;
import com.salesianostriana.dam.model.Producto;
import com.salesianostriana.dam.service.EstablecimientoService;
import com.salesianostriana.dam.service.ImagenService;
import com.salesianostriana.dam.service.ProductoService;

@Component
public class ConversorProducto {
	
	/*private static ProductoService productoService;
	
	@Autowired
	public void setServicios(ProductoService productoService) {
		ConversorProducto.productoService = productoService;

	}*/

	private static ProductoService service;
	private static EstablecimientoService estService;
	private static ImagenService imgService;
	private static ConversorEstablecimiento converter;
	private static ConversorImagen imagenConverter;
	
	@Autowired
	public void setServicios(ProductoService service, ConversorEstablecimiento converter, EstablecimientoService estService, ConversorImagen imagenConverter, ImagenService imgService) {
		ConversorProducto.service = service;
		ConversorProducto.converter = converter;
		ConversorProducto.estService = estService;
		ConversorProducto.imagenConverter = imagenConverter;
		ConversorProducto.imgService = imgService;
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
	
	public ListaProductosDto converterListaProductoDto(Producto producto) {
		
		
		return ListaProductosDto.builder()
				.id(producto.getId())
				.nombre(producto.getNombre())
				.precio(String.valueOf(producto.getPrecio()))
				.build();
		

		
	}
	
	public ProductoDto2 productoToProductoDto2(Producto producto) {
		EstablecimientoDto localDto;
		ImagenDto imagenDto = new ImagenDto();
		
		localDto = converter.establecimientoToEstablecimientoDto(estService.findById(producto.getEstablecimiento().getId()));
		if(producto.getImagen() != null) {
			imagenDto = imagenConverter.imagenToImagenDto(imgService.findById(producto.getImagen().getId()));
		}
		
		return ProductoDto2.builder()
				.id(producto.getId())
				.nombre(producto.getNombre())
				.descripcion(producto.getDescripcion())
				.precio(producto.getPrecio())
				.activo(producto.isActivo())
				.glucosa(producto.isGluten())
				.lactosa(producto.isLactosa())
				.establecimiento(localDto)
				.imagen(imagenDto)
				.build();
	}

}
