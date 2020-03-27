package com.salesianostriana.dam.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.dto.CreateEstablecimientoDto;
import com.salesianostriana.dam.model.Categoria;
import com.salesianostriana.dam.model.Establecimiento;
import com.salesianostriana.dam.model.Gerente;
import com.salesianostriana.dam.model.Imagen;
import com.salesianostriana.dam.model.Ubicacion;
import com.salesianostriana.dam.repository.EstablecimientoRepository;

@Service
public class EstablecimientoService extends BaseService<Establecimiento, Long, EstablecimientoRepository> {
	
	public List<Establecimiento> findAllFavs() {
	
		List<Establecimiento> listaFavs = new ArrayList<>();
		List<Establecimiento> listaDeTodos = new ArrayList<>();
		listaDeTodos = repositorio.findAll();
		
		for(Establecimiento e:listaDeTodos) {
			if(e.isFav()== true) {
				listaFavs.add(e);
				
			}
			
		}
		
		return listaFavs;
		
	}

	
	public Establecimiento newEstablecimiento(CreateEstablecimientoDto createEstablecimientoDto, Imagen imagen, Ubicacion ubicacion, Gerente gerente, Categoria categoria ) {
		
		Establecimiento newEstablecimiento = Establecimiento.builder()
				.nombre(createEstablecimientoDto.getNombre())
				.descripcion(createEstablecimientoDto.getDescripción())
				.presupuesto(createEstablecimientoDto.getPresupuesto())
				.horaApertura(createEstablecimientoDto.getHoraApertura())
				.horaCierre(createEstablecimientoDto.getHoraCierre())
				.localizacion(ubicacion)
				.gerente(gerente)
				.categoria(categoria)
				.imagen(imagen).build();
		
		return this.repositorio.save(newEstablecimiento);
	}

}
