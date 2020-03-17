package com.salesianostriana.dam.model;

import javax.persistence.Entity;

import com.salesianostriana.dam.model.Avatar.AvatarBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@AllArgsConstructor
@Entity @Builder
public class Ubicacion {
	private double latitud;
	private double longitud;
}
