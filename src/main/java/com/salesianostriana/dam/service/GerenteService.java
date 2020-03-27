package com.salesianostriana.dam.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.salesianostriana.dam.dto.CreateGerenteDto;
import com.salesianostriana.dam.model.Avatar;
import com.salesianostriana.dam.model.Cliente;
import com.salesianostriana.dam.model.Establecimiento;
import com.salesianostriana.dam.model.Gerente;
import com.salesianostriana.dam.model.Role;
import com.salesianostriana.dam.repository.GerenteRepository;

@Service
public class GerenteService extends BaseService<Gerente,Long,GerenteRepository> {
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public Gerente findFirstByEmail(String email) {
		return this.repositorio.findFirstByEmail(email);
	}
	
	public Gerente newGerente(CreateGerenteDto createGerenteDto, Avatar avatar) {
		
		Set<Role> roles = new HashSet<Role>();
		
		roles.add(Enum.valueOf(Role.class, "GERENTE"));


			Gerente newGerente = Gerente.builder()
					.username(createGerenteDto.getUsername())
					.email(createGerenteDto.getEmail())
					.password(encoder.encode(createGerenteDto.getPassword()))
					.apellidos(createGerenteDto.getApellidos())
					.nombre(createGerenteDto.getNombre())
					.edad(createGerenteDto.getEdad())
					.roles(roles)
					.avatar(avatar)
					.tlfContacto(createGerenteDto.getTelfContacto())
					.build();
					

			return this.repositorio.save(newGerente);
		}
	

	public List<Gerente> listAllClientes() {	
		List<Gerente> listaDeTodos = new ArrayList<>();		
		listaDeTodos = repositorio.findAll();
		
		
		return listaDeTodos;
	}
		

	public Gerente buscarPorEstablecimiento(Establecimiento e) {
		return this.repositorio.findByEstablecimiento(e);

	}


}
