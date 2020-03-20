package com.salesianostriana.dam.model;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@AllArgsConstructor
@Entity@Builder
@Table(name = "horario")
public class Horario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private LocalTime horaComienzo;
	private LocalTime horaCierre;
	
	@ElementCollection
	private List<LocalTime> horasRecogida;
	
	private String dia;
	
	private boolean esFestivo;
	
	@ManyToOne
	private Establecimiento establecimiento;
	
	

}
