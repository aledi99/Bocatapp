package com.salesianostriana.dam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.dto.CreateProductoDto;
import com.salesianostriana.dam.model.Gerente;
import com.salesianostriana.dam.model.Producto;
import com.salesianostriana.dam.repository.ProductoRepository;

@Service
public class ProductoService extends BaseService<Producto, Long, ProductoRepository>{
	
	@Autowired
	private GerenteService gerService;
	
	@Autowired
	private EstablecimientoService estService;

	private OAuth2Authentication oauth;
	
	public Producto newProducto(CreateProductoDto createProductoDto) {
		String username = (String) oauth.getUserAuthentication().getPrincipal();
		
		Gerente gerente = gerService.findFirstByEmail(username);
		
		Producto producto = Producto.builder()
				.nombre(createProductoDto.getNombre())
				.descripcion(createProductoDto.getDescripcion())
				.lactosa(createProductoDto.isLactosa())
				.gluten(createProductoDto.isGlucosa())
				.activo(true)
				.establecimiento(estService.findById(gerente.getEstablecimiento().getId()))
				.build();
		
		return this.repositorio.save(producto);
	}



}
