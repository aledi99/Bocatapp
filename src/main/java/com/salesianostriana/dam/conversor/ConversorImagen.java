package com.salesianostriana.dam.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesianostriana.dam.dto.ImagenDto;
import com.salesianostriana.dam.model.Imagen;
import com.salesianostriana.dam.service.ImagenService;

@Component
public class ConversorImagen {
	
	private static ImagenService service;
	
	@Autowired
	public void setServicios(ImagenService service) {
		ConversorImagen.service = service;
	}
	
	
	public ImagenDto imagenToImagenDto(Imagen imagen) {
		return ImagenDto.builder()
				.id(imagen.getId())
				.nombreFichero(imagen.getNombreFichero())
				.tamanyo(imagen.getTamanyo())
				.tipoFichero(imagen.getTipoFichero())
				.uriDescargaFichero(imagen.getUriDescargaFichero())
				.build();
	}

}
