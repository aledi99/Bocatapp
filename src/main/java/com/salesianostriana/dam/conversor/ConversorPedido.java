package com.salesianostriana.dam.conversor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesianostriana.dam.dto.PedidoDto;
import com.salesianostriana.dam.dto.ProductoDto2;
import com.salesianostriana.dam.model.Pedido;
import com.salesianostriana.dam.model.Producto;
import com.salesianostriana.dam.service.LineaPedidoService;
import com.salesianostriana.dam.service.PedidoService;

@Component
public class ConversorPedido {
	
	
	private static PedidoService service;
	private static ConversorProducto prodConverter;
	private static LineaPedidoService lpService;
	
	@Autowired
	public void setServicios(PedidoService service, ConversorProducto lpConverter, LineaPedidoService lpService) {
		ConversorPedido.prodConverter = lpConverter;
		ConversorPedido.service = service;
		ConversorPedido.lpService = lpService;
	}
	
	public PedidoDto pedidoToPedidoDto(Pedido pedido) {
		List<ProductoDto2> listaProducto = null;
		List<Producto> listas = null;
		
		listas = pedido.getProductos();
		
		if(pedido.getProductos() != null) {
			for(int i = 0; i < listas.size(); i++) {
			listaProducto.add(prodConverter.productoToProductoDto2(listas.get(i)));
		}
		}

		return PedidoDto.builder()
				.entregado(pedido.isEntregado())
				.preparado(pedido.isPreparado())
				.fechaPedido(pedido.getFechaPedido())
				.horaPedido(pedido.getHoraPedido())
				.total(pedido.getTotal())
				.lineasPedido(listaProducto)
				.build();
	}


}
