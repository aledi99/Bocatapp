package com.salesianostriana.dam.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesianostriana.dam.dto.LineaPedidoDto;
import com.salesianostriana.dam.dto.ProductoDto2;
import com.salesianostriana.dam.model.LineaPedido;
import com.salesianostriana.dam.service.LineaPedidoService;
import com.salesianostriana.dam.service.ProductoService;

@Component
public class ConversorLineaPedido {
	
	private static LineaPedidoService service;
	private static ProductoService prodService;
	private static ConversorProducto prodConverter;
	
	@Autowired
	public void setServicios(LineaPedidoService service, ConversorProducto prodConverter) {
		ConversorLineaPedido.service = service;
		ConversorLineaPedido.prodConverter = prodConverter;
		ConversorLineaPedido.prodService = prodService;
	}
	
	LineaPedidoDto lineaPedidoToLineaPedidoDto(LineaPedido lineaPedido) {
		ProductoDto2 producto;
		
		producto = prodConverter.productoToProductoDto2(prodService.findById(lineaPedido.getProducto().getId()));
		
		return LineaPedidoDto.builder()
				.id(lineaPedido.getId())
				.cantidad(lineaPedido.getCantidad())
				.precioCantidad(lineaPedido.precioTotal())
				.producto(producto)
				.build();
				
	}

}
