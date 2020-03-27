package com.salesianostriana.dam.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToOne;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@DiscriminatorValue("B")
public class Gerente extends Usuario {
	

	@GeneratedValue
	private long idGerente;

	//private Establecimiento local;
	private boolean validado;
	private String tlfContacto;
	
	
	
	@Builder
	public Gerente(Long id, String nombre, String username, String apellidos, Integer edad, String email,
			String password, long[] favoritos, Ubicacion localizacion, Avatar avatar, Set<Role> roles,
			Date fechaCreacion, LocalDateTime lastPasswordChangedAt, long idGerente, boolean validado, String tlfContacto) {
		super(id, nombre, username, apellidos, edad, email, password, favoritos, localizacion, avatar, roles,
				fechaCreacion, lastPasswordChangedAt);
		this.idGerente = idGerente;
		this.validado = validado;
		this.tlfContacto = tlfContacto;
	}
	
	public Gerente() {
		super();
	}
	
	@OneToOne(fetch = FetchType.EAGER)
	private Establecimiento establecimiento;
}
