package com.salesianostriana.dam.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@AllArgsConstructor
@Entity@Builder
@Table(name = "linea_pedido")
public class LineaPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private int cantidad;
	 
	@OneToOne
	private Producto producto;
	
	@ManyToOne
	private Pedido pedido;
	
	public double precioTotal() {
		return producto.getPrecio() * cantidad;
	}
}
