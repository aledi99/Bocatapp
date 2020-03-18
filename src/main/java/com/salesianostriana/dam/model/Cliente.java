package com.salesianostriana.dam.model;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

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
	
	private long idCliente;
	private boolean validado;
	
	
	
	
	@Builder.Default
	private LocalDateTime lastPasswordChangedAt = LocalDateTime.now();

}