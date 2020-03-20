package com.salesianostriana.dam.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesianostriana.dam.dto.CategoriaDto;
import com.salesianostriana.dam.dto.ListaEstablecimientoDto;
import com.salesianostriana.dam.model.Establecimiento;
import com.salesianostriana.dam.service.CategoriaService;
import com.salesianostriana.dam.service.EstablecimientoService;

@Component
public class ConversorEstablecimiento {
	
	private static EstablecimientoService etService;
	private static CategoriaService catService;
	private static ConversorCategoria catConverter;
	
	@Autowired
	public void setServicios(CategoriaService catService,
			EstablecimientoService etService, ConversorCategoria catConverter) {
		ConversorEstablecimiento.catService = catService;
		ConversorEstablecimiento.etService = etService;
		ConversorEstablecimiento.catConverter = catConverter;

	}
	
	
	public ListaEstablecimientoDto establecimientoFilterDto(Establecimiento establecimiento) {
		CategoriaDto categoria;
		
		categoria = catConverter.categoriaToCategoriaDto(catService.findById(establecimiento.getCategoria().getId()));

		
		return ListaEstablecimientoDto.builder()
				.id(establecimiento.getId())
				.nombre(establecimiento.getNombre())
				.descripcion(establecimiento.getDescripcion())
				.categoria(categoria)
				.build();
		
	}


}
