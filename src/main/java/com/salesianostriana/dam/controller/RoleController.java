package com.salesianostriana.dam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.salesianostriana.dam.model.Admin;
import com.salesianostriana.dam.model.Cliente;
import com.salesianostriana.dam.model.Gerente;
import com.salesianostriana.dam.model.Role;
import com.salesianostriana.dam.service.AdminService;
import com.salesianostriana.dam.service.ClienteService;
import com.salesianostriana.dam.service.GerenteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

	private final ClienteService clienteService;
	private final GerenteService gerenteService;
	private final AdminService adminService;

	@GetMapping("/{username}")
	@ResponseBody
	public ResponseEntity<?> getRole(@PathVariable String username) {
		String role = null;

		if (gerenteService.findFirstByEmail(username) != null) {
			Gerente gerente = gerenteService.findFirstByEmail(username);

			for (Role rol : gerente.getRoles()) {
				role = rol.name();
			}

			return ResponseEntity.ok().body(role);
		}

		if (adminService.findFirstByEmail(username) != null) {
			Admin admin = adminService.findFirstByEmail(username);

			for (Role rol : admin.getRoles()) {
				role = rol.name();
			}

			return ResponseEntity.ok().body(role);
		}

		if (clienteService.findFirstByEmail(username) != null) {
			Cliente cliente = clienteService.findFirstByEmail(username);

			for (Role rol : cliente.getRoles()) {
				role = rol.name();
			}

			return ResponseEntity.ok().body(role);
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay un producto con este id.");

	}

}
