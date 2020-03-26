package com.salesianostriana.dam.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
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
@DiscriminatorValue("C")
public class Cliente extends Usuario {
	

	@GeneratedValue
	private long idCliente;
	private boolean validado;
	


	
	
	@Builder
	public Cliente(Long id, String nombre, String username, String apellidos, Integer edad,
			  String email, String password, long[] favoritos, Ubicacion localizacion, Avatar avatar,
			 Set<Role> roles, Date fechaCreacion, LocalDateTime lastPasswordChangedAt, long idCliente,
			boolean validado) {
		super(id, nombre, username, apellidos, edad, email, password, favoritos, localizacion, avatar, roles,
				fechaCreacion, lastPasswordChangedAt);
		this.idCliente = idCliente;
		this.validado = validado;
	}
	

	public Cliente() {
		super();
	}


	@Builder.Default
	private LocalDateTime lastPasswordChangedAt = LocalDateTime.now();

}
