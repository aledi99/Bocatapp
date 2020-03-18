package com.salesianostriana.dam.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.model.Usuario;
import com.salesianostriana.dam.repository.UsuarioRepository;

@Service
public class UsuarioService extends BaseService<Usuario, Long, UsuarioRepository> {
	
	public Usuario findFirstByEmail(String email) {
		return this.repositorio.findFirstByEmail(email);
	}

}
