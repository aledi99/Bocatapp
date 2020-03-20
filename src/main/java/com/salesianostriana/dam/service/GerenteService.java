package com.salesianostriana.dam.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.dto.CreateGerenteDto;
import com.salesianostriana.dam.model.Gerente;
import com.salesianostriana.dam.model.Role;
import com.salesianostriana.dam.repository.GerenteRepository;

@Service
public class GerenteService extends BaseService<Gerente,Long,GerenteRepository> {
	
	public Gerente newGerente(CreateGerenteDto createGerenteDto) {
		
		Set<Role> roles = new HashSet<Role>();
		
		roles.add(Enum.valueOf(Role.class, "GERENTE"));


		Gerente newGerente = Gerente.builder()
				.username(createGerenteDto.getUsername())
				.email(createGerenteDto.getEmail())
				.password(createGerenteDto.getPassword())
				.apellidos(createGerenteDto.getApellidos())
				.nombre(createGerenteDto.getNombre())
				.roles(roles)
				.validado(true)
				.build();
				

		return this.repositorio.save(newGerente);
	}


}
