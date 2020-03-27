package com.salesianostriana.dam.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoDto {
	
	private long id;
	private String horaPedido;
	private String fechaPedido;
	private double total;
	private boolean preparado;
	private boolean entregado;
	private List<ProductoDto2> lineasPedido;

}
