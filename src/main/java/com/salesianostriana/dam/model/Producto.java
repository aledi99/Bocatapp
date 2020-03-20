package com.salesianostriana.dam.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Table(name = "producto")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nombre;
	private double precio;
	private int cantidad;
	
	@OneToOne
	private Imagen imagen;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Ingrediente> ingredientes;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Carta carta;
	

}
