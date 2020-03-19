package com.salesianostriana.dam.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import com.salesianostriana.dam.model.Admin.AdminBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@DiscriminatorValue("C")
public class Cliente extends Usuario {
	
	@GeneratedValue
	private long idCliente;
	private boolean validado;
	
	
	
	
	
	
	public Cliente(Long id, String nombre, String username, String apellidos, Integer edad, String email,
			String password, long[] favoritos, Ubicacion localizacion, Avatar avatar, Set<Role> roles,
			Date fechaCreacion, LocalDateTime lastPasswordChangedAt) {
		super(id, nombre, username, apellidos, edad, email, password, favoritos, localizacion, avatar, roles, fechaCreacion,
				lastPasswordChangedAt);
		
	}






	@Builder.Default
	private LocalDateTime lastPasswordChangedAt = LocalDateTime.now();

}
