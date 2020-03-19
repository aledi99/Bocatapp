package com.salesianostriana.dam.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private long id;
	
	private int cantidad;
	private double precio;
	 
	@OneToOne
	private Producto producto;
}
