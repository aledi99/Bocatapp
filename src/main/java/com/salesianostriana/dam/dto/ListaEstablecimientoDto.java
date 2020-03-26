package com.salesianostriana.dam.dto;

import java.time.LocalTime;

import com.salesianostriana.dam.model.Ubicacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ListaEstablecimientoDto {
	
	private long id;
	private String nombre;
	private String descripcion;
	private double presupuesto;
	private boolean abierto;
	private float valoracion;
	private ImagenDto imagen;
	private UbicacionDto localizacion;
	private String horaApertura;
	private String horaCierre;
	private GerenteDto2 gerente; 
	private CategoriaDto categoria;

}
