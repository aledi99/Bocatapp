package com.salesianostriana.dam.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.salesianostriana.dam.conversor.ConversorPedido;
import com.salesianostriana.dam.dto.PedidoDto;
import com.salesianostriana.dam.model.Gerente;
import com.salesianostriana.dam.model.Pedido;
import com.salesianostriana.dam.service.GerenteService;
import com.salesianostriana.dam.service.PedidoService;

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
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay establecimiento con ese Id.");
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
