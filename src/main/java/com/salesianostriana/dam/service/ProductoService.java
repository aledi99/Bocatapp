package com.salesianostriana.dam.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.model.Producto;
import com.salesianostriana.dam.repository.EstablecimientoRepository;
import com.salesianostriana.dam.repository.ProductoRepository;

@Service
public class ProductoService extends BaseService<Producto, Long, ProductoRepository> {

}
