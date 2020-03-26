package com.salesianostriana.dam.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.model.Categoria;
import com.salesianostriana.dam.repository.CategoriaRepository;

@Service
public class CategoriaService extends BaseService<Categoria, Long, CategoriaRepository> {

	public Categoria buscarPorNombre(String n) {
		return this.repositorio.findByNombre(n);
	}
}
