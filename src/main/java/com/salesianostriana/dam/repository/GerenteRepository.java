package com.salesianostriana.dam.repository;

import com.salesianostriana.dam.model.Establecimiento;
import com.salesianostriana.dam.model.Gerente;

public interface GerenteRepository extends UsuarioRepository<Gerente>{
	
	Gerente findByEstablecimiento(Establecimiento establecimiento);

}
