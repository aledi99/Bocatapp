package com.salesianostriana.dam.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import lombok.ToString;

@Data @NoArgsConstructor
@AllArgsConstructor
@Entity@Builder
@Table(name = "producto")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	private String descripcion;
	private double precio;
	private boolean activo;
	private boolean gluten;
	private boolean lactosa;
	
	@ToString.Exclude
	@ManyToOne (fetch =FetchType.EAGER)
	private Establecimiento establecimiento;
	
	@OneToOne
	private Imagen imagen;

	

}
