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
@DiscriminatorValue("A")
public class Admin extends Usuario {
	

	@GeneratedValue
	private long idAdmin;
	
	


	@Builder
	public Admin(Long id, String nombre, String username, String apellidos, Integer edad, String email, String password,
			long[] favoritos, Ubicacion localizacion, Avatar avatar, Set<Role> roles, Date fechaCreacion,
			LocalDateTime lastPasswordChangedAt, long idAdmin, LocalDateTime lastPasswordChangedAt2) {
		super(id, nombre, username, apellidos, edad, email, password, favoritos, localizacion, avatar, roles,
				fechaCreacion, lastPasswordChangedAt);
		this.idAdmin = idAdmin;
	}


	public Admin() {
		super();
	}



	
	


	

}
