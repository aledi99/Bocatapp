package com.salesianostriana.dam.model;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@DiscriminatorValue("A")
public class Admin extends Usuario {
	
	@GeneratedValue
	private long idAdmin;
	
	
	@Builder.Default
	private LocalDateTime lastPasswordChangedAt = LocalDateTime.now();
	

}
