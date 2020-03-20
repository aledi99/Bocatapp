package com.salesianostriana.dam.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.salesianostriana.dam.model.Ubicacion.UbicacionBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@AllArgsConstructor
@Entity@Builder
@Table(name = "establecimiento")
public class Establecimiento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private double presupuesto;
	private float valoracion;
	
	@OneToOne
	private Gerente gerente;
	
	@OneToMany
	private List<Carta> cartas;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Ubicacion localizacion;
	
	@OneToMany
	private List<Horario> horarios;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Pago> pagos;
	
	@OneToMany
	private List<Pedido> pedidos;
	
	@OneToOne
	private Categoria categoria;
	
	@OneToMany
	private List<Imagen> imagenes;
	

}
