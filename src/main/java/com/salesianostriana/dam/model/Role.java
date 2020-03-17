package com.salesianostriana.dam.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum Role {
	CLIENTE ("CLIENTE"),
	GERENTE ("GERENTE"),
	ADMIN ("ADMIN");
	private String descripcion;
}
