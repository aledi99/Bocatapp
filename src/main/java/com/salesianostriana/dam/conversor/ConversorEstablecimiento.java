package com.salesianostriana.dam.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesianostriana.dam.dto.CategoriaDto;
import com.salesianostriana.dam.dto.CreateEstablecimientoDto;
import com.salesianostriana.dam.dto.EstablecimientoDto;
import com.salesianostriana.dam.dto.GerenteDto2;
import com.salesianostriana.dam.dto.ImagenDto;
import com.salesianostriana.dam.dto.ListaEstablecimientoDto;
import com.salesianostriana.dam.dto.UbicacionDto;
import com.salesianostriana.dam.model.Establecimiento;
import com.salesianostriana.dam.service.CategoriaService;
import com.salesianostriana.dam.service.EstablecimientoService;
import com.salesianostriana.dam.service.GerenteService;
import com.salesianostriana.dam.service.ImagenService;
import com.salesianostriana.dam.service.UbicacionService;

@Component
public class ConversorEstablecimiento {
	
	private static EstablecimientoService etService;
	private static CategoriaService catService;
	private static GerenteService gerService;
	private static ConversorCategoria catConverter;
	private static ConversorGerente gerConverter;
	private static ConversorUbicacion ubConverter;
	private static UbicacionService ubService;
	private static ConversorImagen imgConverter;
	private static ImagenService imgService;
	
	@Autowired
	public void setServicios(CategoriaService catService,
			EstablecimientoService etService, ConversorCategoria catConverter, ConversorGerente gerConverter, GerenteService gerService, UbicacionService ubService, ConversorUbicacion ubConverter, ConversorImagen imgConverter, ImagenService imgService) {
		ConversorEstablecimiento.catService = catService;
		ConversorEstablecimiento.etService = etService;
		ConversorEstablecimiento.catConverter = catConverter;
		ConversorEstablecimiento.gerConverter = gerConverter;
		ConversorEstablecimiento.gerService = gerService;
		ConversorEstablecimiento.ubConverter = ubConverter;
		ConversorEstablecimiento.ubService = ubService;
		ConversorEstablecimiento.imgConverter = imgConverter;
		ConversorEstablecimiento.imgService = imgService;
	}
	
	
	public ListaEstablecimientoDto establecimientoFilterDto(Establecimiento establecimiento) {
		CategoriaDto categoria = null;
		GerenteDto2 gerente = null;
		UbicacionDto ubicacion = null;
		ImagenDto imagen = null;
		
		
		if(establecimiento.getCategoria() != null) {
			categoria = catConverter.categoriaToCategoriaDto(catService.findById(establecimiento.getCategoria().getId()));
		}
		
		
		if(establecimiento.getGerente() != null) {
		gerente = gerConverter.gerenteToGerenteDto(gerService.buscarPorEstablecimiento(establecimiento));

		}
		
		if(establecimiento.getLocalizacion() != null) {
		ubicacion = ubConverter.ubicacionToUbicacionDto(ubService.findById(establecimiento.getLocalizacion().getId()));
		}
		
		if(establecimiento.getImagen() != null) {
		imagen = imgConverter.imagenToImagenDto(imgService.findById(establecimiento.getImagen().getId()));
		}
		
		return ListaEstablecimientoDto.builder()
				.id(establecimiento.getId())
				.nombre(establecimiento.getNombre())
				.descripcion(establecimiento.getDescripcion())
				.abierto(establecimiento.isAbierto())
				.imagen(imagen)
				.localizacion(ubicacion)
				.horaApertura(establecimiento.getHoraApertura())
				.horaCierre(establecimiento.getHoraCierre())
				.gerente(gerente)
				.categoria(categoria)
				.build();
		
	}
	
	public EstablecimientoDto establecimientoToEstablecimientoDto(Establecimiento establecimiento) {
		return EstablecimientoDto.builder()
				.id(establecimiento.getId())
				.nombre(establecimiento.getNombre())
				.descripcion(establecimiento.getDescripcion())
				.abierto(establecimiento.isAbierto())
				.valoracion(establecimiento.getValoracion())
				.horaApertura(establecimiento.getHoraApertura())
				.horaCierre(establecimiento.getHoraCierre())
				.build();
	}

	public CreateEstablecimientoDto convertEstablecimientotoEstablecimientoDto(Establecimiento establecimiento) {		
		
		return CreateEstablecimientoDto.builder()
				.nombre(establecimiento.getNombre())
				.presupuesto(establecimiento.getPresupuesto())
				.descripción(establecimiento.getDescripcion())
				.build();
		

		
	}
	
	public Establecimiento convertEstablecimientoDtoToEstablecimiento(CreateEstablecimientoDto createEstablecimientoDto) {
		return Establecimiento.builder()
				.nombre(createEstablecimientoDto.getNombre())
				.presupuesto(createEstablecimientoDto.getPresupuesto())
				.descripcion(createEstablecimientoDto.getDescripción())
				.build();
	}


}
