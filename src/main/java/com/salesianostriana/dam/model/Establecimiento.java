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

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor
@AllArgsConstructor
@Entity@Builder
@Table(name = "establecimiento")
public class Establecimiento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	private String descripcion;
	private double presupuesto;
	private boolean abierto;
	private float valoracion;
	private String horaApertura;
	private String horaCierre;
	private boolean fav;
	
	@OneToOne
	private Gerente gerente;
	
	@JsonBackReference
	@ToString.Exclude
	@OneToMany(mappedBy="establecimiento")
	private List<Producto> producto;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Ubicacion localizacion;
	
	@JsonBackReference
	@ToString.Exclude
	@OneToMany(fetch = FetchType.EAGER)
	private List<Pago> pagos;
	
	@JsonBackReference
	@ToString.Exclude
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "establecimiento")
	private List<Pedido> pedidos;
	
	@OneToOne
	private Categoria categoria;
	
	@OneToOne
	private Imagen imagen;
	

}
