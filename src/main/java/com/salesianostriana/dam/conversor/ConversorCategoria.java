package com.salesianostriana.dam.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesianostriana.dam.dto.CategoriaDto;
import com.salesianostriana.dam.dto.CategoriaDtoName;
import com.salesianostriana.dam.dto.CreateCategoriaDto;
import com.salesianostriana.dam.dto.CreateEstablecimientoDto;
import com.salesianostriana.dam.model.Categoria;
import com.salesianostriana.dam.model.Establecimiento;
import com.salesianostriana.dam.model.Producto;
import com.salesianostriana.dam.service.CategoriaService;
import com.salesianostriana.dam.service.EstablecimientoService;

@Component
public class ConversorCategoria {
	
	private static CategoriaService catService;
	
	@Autowired
	public void setServicios(CategoriaService catService,
			EstablecimientoService etService) {
		ConversorCategoria.catService = catService;

	}

	
	public CategoriaDto categoriaToCategoriaDto(Categoria categoria) {
		return CategoriaDto.builder()
				.id(categoria.getId())
				.nombre(categoria.getNombre())
				.build();
	}
	
	public CategoriaDtoName categoriaToCategoriaDtoName(Categoria categoria) {
		return CategoriaDtoName.builder()
				.nombre(categoria.getNombre())
				.build();
	}
	
	public Categoria convertCategoriaDtotoCategoria(CreateCategoriaDto createCategoriaDto) {
		return Categoria.builder()
				.nombre(createCategoriaDto.getNombre())
				.build();
	}
	
	


}
