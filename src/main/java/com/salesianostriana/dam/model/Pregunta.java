package com.salesianostriana.dam.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@AllArgsConstructor
@Entity@Builder
@Table(name = "pregunta")
public class Pregunta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String titulo;
	private String cuerpo;
	private LocalDate fechaCreacion;
	
	@OneToMany
	private List<Comentario> comentarios;
	
	@ManyToOne
	private Establecimiento establecimiento;
	
	@ManyToOne
	private Usuario usuario;

}
