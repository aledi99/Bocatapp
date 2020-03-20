package com.salesianostriana.dam.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.dto.CreateAdminDto;
import com.salesianostriana.dam.model.Admin;
import com.salesianostriana.dam.model.Role;
import com.salesianostriana.dam.repository.AdminRepository;

@Service
public class AdminService extends BaseService<Admin,Long,AdminRepository>{
	
	public Admin newAdmin(CreateAdminDto createAdminDto) {
		
		Set<Role> roles = new HashSet<Role>();
		
		roles.add(Enum.valueOf(Role.class, "ADMIN"));


		Admin newAdmin = Admin.builder()
				.username(createAdminDto.getUsername())
				.email(createAdminDto.getEmail())
				.password(createAdminDto.getPassword())
				.apellidos(createAdminDto.getApellidos())
				.nombre(createAdminDto.getNombre())
				.roles(roles)
				.build();
				

		return this.repositorio.save(newAdmin);
	}


}
