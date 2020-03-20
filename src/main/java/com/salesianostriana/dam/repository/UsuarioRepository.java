package com.salesianostriana.dam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.salesianostriana.dam.model.Usuario;

@NoRepositoryBean
public interface UsuarioRepository<T extends Usuario> extends JpaRepository<T, Long> {
	T findFirstByEmail(String email);

}
