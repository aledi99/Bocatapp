package com.salesianostriana.dam.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.salesianostriana.dam.model.Avatar.AvatarBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@AllArgsConstructor
@Entity@Builder
public class Ubicacion {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private double latitud;
	private double longitud;
}
