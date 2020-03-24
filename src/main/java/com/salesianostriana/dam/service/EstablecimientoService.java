package com.salesianostriana.dam.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.model.Establecimiento;
import com.salesianostriana.dam.repository.EstablecimientoRepository;

@Service
public class EstablecimientoService extends BaseService<Establecimiento, Long, EstablecimientoRepository> {
	
	public List<Establecimiento> findAllFavs() {
		Boolean Fav = true;
		List<Establecimiento> listaFavs = new ArrayList<>();
		List<Establecimiento> listaDeTodos = new ArrayList<>();
		listaDeTodos = repositorio.findAll();
		
		for(Establecimiento e:listaDeTodos) {
			if(Fav.equals(true)) {
				listaFavs.add(e);
				
			}else {
				listaFavs = null;
			}
			
		}
		
		return listaFavs;
		
	}

}
