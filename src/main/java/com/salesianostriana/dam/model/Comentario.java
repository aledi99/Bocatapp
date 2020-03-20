package com.salesianostriana.dam.model;

import java.time.LocalDate;

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
@Table(name = "comentario")
public class Comentario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String cuerpo;
	private LocalDate fechaCreacion;
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Pregunta pregunta;

}
