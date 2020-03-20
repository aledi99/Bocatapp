package com.salesianostriana.dam.model;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@DiscriminatorValue("B")
public class Gerente extends Usuario {
	
	private Long idGerente;
	//private Establecimiento local;
	private boolean validado;
	
	@Builder.Default
	private LocalDateTime lastPasswordChangedAt = LocalDateTime.now();

	@OneToOne(fetch = FetchType.EAGER)
	private Establecimiento establecimiento;
}
