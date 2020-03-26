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
import lombok.ToString;

@Data @NoArgsConstructor
@AllArgsConstructor
@Entity@Builder
@Table(name = "pedido")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String horaPedido;
	private String fechaPedido;
	private double total;
	private boolean preparado;
	private boolean entregado;
	
	@OneToOne
	private Pago formaPago;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<LineaPedido> lineasPedido;
	
	@ToString.Exclude
	@ManyToOne
	private Establecimiento establecimiento;
	
	@ManyToOne
	private Usuario usuario;
	

}
