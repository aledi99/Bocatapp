package com.salesianostriana.dam.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@AllArgsConstructor
@Entity@Builder
@Table(name = "pedido")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private long idEstablecimiento;
	private LocalTime horaPedido;
	private LocalDate fechaPedido;
	private double total;
	
	@OneToOne
	private Horario horaRecogida;
	
	@OneToOne
	private Pago formaPago;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<LineaPedido> lineasPedido;
	
	@ManyToOne
	private Establecimiento establecimiento;
	
	@ManyToOne
	private Usuario usuario;
	

}
