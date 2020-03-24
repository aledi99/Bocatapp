package com.salesianostriana.dam.model;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@AllArgsConstructor
@Entity@Builder
@Table(name = "establecimiento")
public class Establecimiento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nombre;
	private String descripcion;
	private double presupuesto;
	private boolean abierto;
	private float valoracion;
	private LocalTime horaApertura;
	private LocalTime horaCierre;
	private boolean fav;
	
	@OneToOne
	private Gerente gerente;
	
	@OneToMany
	private List<Producto> producto;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Ubicacion localizacion;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Pago> pagos;
	
	@OneToMany
	private List<Pedido> pedidos;
	
	@OneToOne
	private Categoria categoria;
	
	@OneToOne
	private Imagen imagen;
	

}
