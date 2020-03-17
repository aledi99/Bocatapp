package com.salesianostriana.dam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findFirstByEmail(String email);

}
