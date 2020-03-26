package com.salesianostriana.dam.conversor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesianostriana.dam.dto.LineaPedidoDto;
import com.salesianostriana.dam.dto.PedidoDto;
import com.salesianostriana.dam.model.LineaPedido;
import com.salesianostriana.dam.model.Pedido;
import com.salesianostriana.dam.service.LineaPedidoService;
import com.salesianostriana.dam.service.PedidoService;

@Component
public class ConversorPedido {
	
	
	private static PedidoService service;
	private static ConversorLineaPedido lpConverter;
	private static LineaPedidoService lpService;
	
	@Autowired
	public void setServicios(PedidoService service, ConversorLineaPedido lpConverter, LineaPedidoService lpService) {
		ConversorPedido.lpConverter = lpConverter;
		ConversorPedido.service = service;
		ConversorPedido.lpService = lpService;
	}
	
	public PedidoDto pedidoToPedidoDto(Pedido pedido) {
		List<LineaPedidoDto> listaLineaPedidoDtos = null;
		List<LineaPedido> listas = null;
		
		listas = pedido.getLineasPedido();
		
		if(pedido.getLineasPedido() != null) {
			for(int i = 0; i < listas.size(); i++) {
			listaLineaPedidoDtos.add(lpConverter.lineaPedidoToLineaPedidoDto(listas.get(i)));
		}
		}

		return PedidoDto.builder()
				.entregado(pedido.isEntregado())
				.preparado(pedido.isPreparado())
				.fechaPedido(pedido.getFechaPedido())
				.horaPedido(pedido.getHoraPedido())
				.total(pedido.getTotal())
				.lineasPedido(listaLineaPedidoDtos)
				.build();
	}


}
