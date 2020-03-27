package com.salesianostriana.dam.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LineaPedidoDto {
	
	private long id;
	private int cantidad;
	private double precioCantidad;
	
	private ProductoDto2 producto;

}
