package com.salesianostriana.dam.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.salesianostriana.dam.conversor.ConversorPedido;
import com.salesianostriana.dam.dto.PedidoDto;
import com.salesianostriana.dam.dto.ProductoDto2;
import com.salesianostriana.dam.model.Admin;
import com.salesianostriana.dam.model.Cliente;
import com.salesianostriana.dam.model.Establecimiento;
import com.salesianostriana.dam.model.Gerente;
import com.salesianostriana.dam.model.Pedido;
import com.salesianostriana.dam.model.Producto;
import com.salesianostriana.dam.service.AdminService;
import com.salesianostriana.dam.service.ClienteService;
import com.salesianostriana.dam.service.EstablecimientoService;
import com.salesianostriana.dam.service.GerenteService;
import com.salesianostriana.dam.service.PedidoService;
import com.salesianostriana.dam.service.ProductoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PedidoController {
	
	@Autowired
	private PedidoService service;
	
	@Autowired
	private ConversorPedido converter;
	
	@Autowired
	private ProductoService prodService;
	
	@Autowired
	private EstablecimientoService estService;
	
	@Autowired
	private ClienteService cliService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private GerenteService gerService;
	
	@GetMapping("/pedidos/me/")
	public ResponseEntity<?> getPedidos(OAuth2Authentication oAuth) {
		List<PedidoDto> pedidos = new ArrayList<>();
		String principal = oAuth.getUserAuthentication().getPrincipal().toString();

		if (gerService.findFirstByEmail(principal) != null) {
			Gerente gerente = gerService.findFirstByEmail(principal);
			
			for(int i = 0; i < gerente.getPedidos().size(); i++) {
				pedidos.add(converter.pedidoToPedidoDto(gerente.getEstablecimiento().getPedidos().get(i)));
			}
			
			return ResponseEntity.ok().body(pedidos);

		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay gerente logueado.");
		}

	}
	
	@PostMapping("/local/{id}/pedido")
	public ResponseEntity<?> doPedido(@PathVariable Optional<Long> id, @RequestBody List<ProductoDto2> list, OAuth2Authentication oauth) {
		List<Producto> productos = null;
		Long theId = id.orElse(-1L);
		
		String email = (String) oauth.getUserAuthentication().getPrincipal();
		
		if(estService.findById(theId) != null) {
			for(int i = 0; i < list.size(); i++) {
			productos.add(prodService.findById(list.get(i).getId()));
		}
			
			Establecimiento establecimiento = estService.findById(theId);
			
			Pedido pedido = Pedido.builder()
					.entregado(false)
					.preparado(false)
					.fechaPedido(LocalDate.now().toString())
					.horaPedido(LocalTime.now().toString())
					.establecimiento(establecimiento)
					.productos(productos)
					.build();
			
			PedidoDto pedidoDto = converter.pedidoToPedidoDto(pedido);
			
			
			if(cliService.findFirstByEmail(email) != null) {
				Cliente cliente = cliService.findFirstByEmail(email);
				cliente.getPedidos().add(pedido);
				
				cliService.edit(cliente);
			}
			
			if(gerService.findFirstByEmail(email) != null) {
				Gerente gerente = gerService.findFirstByEmail(email);
				gerente.getPedidos().add(pedido);
				
				gerService.edit(gerente);
			}
			
			if(adminService.findFirstByEmail(email) != null) {
				Admin admin = adminService.findFirstByEmail(email);
				admin.getPedidos().add(pedido);
				
				adminService.edit(admin);
			}
			
			return new ResponseEntity<>(pedidoDto, HttpStatus.CREATED);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe establecimiento con ese id.");
		}
		
		
		
	}
	
	@PutMapping("pedidos/{id}/preparado")
	public ResponseEntity<?> estaPreparado(@PathVariable Optional<Long> id) {
		Long theId = id.orElse(-1L);
		
		if(service.findById(theId) != null) {
			Pedido pedido = service.findById(theId);

				pedido.setPreparado(true);
				
				service.edit(pedido);
				return ResponseEntity.accepted().build();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay pedido con ese Id.");
		}
		
	}
		
		@PutMapping("pedidos/{id}/entregado")
		public ResponseEntity<?> estaEntregado(@PathVariable Optional<Long> id) {
			Long theId = id.orElse(-1L);
			
			if(service.findById(theId) != null) {
				Pedido pedido = service.findById(theId);
					pedido.setEntregado(true);
					
					service.edit(pedido);
					return ResponseEntity.accepted().build();
			} else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay pedido con ese Id.");
			}
			
		}
	


}
