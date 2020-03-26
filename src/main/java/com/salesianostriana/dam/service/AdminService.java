package com.salesianostriana.dam.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.dto.CreateAdminDto;
import com.salesianostriana.dam.model.Admin;
import com.salesianostriana.dam.model.Avatar;
import com.salesianostriana.dam.model.Establecimiento;
import com.salesianostriana.dam.model.Role;
import com.salesianostriana.dam.repository.AdminRepository;

@Service
public class AdminService extends BaseService<Admin,Long,AdminRepository>{
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public Admin findFirstByEmail(String email) {
		return this.repositorio.findFirstByEmail(email);
	}
	
	public Admin newAdmin(CreateAdminDto createAdminDto, Avatar avatar) {
		
		Set<Role> roles = new HashSet<Role>();
		
		roles.add(Enum.valueOf(Role.class, "ADMIN"));


			Admin newAdmin = Admin.builder()
					.username(createAdminDto.getUsername())
					.email(createAdminDto.getEmail())
					.password(encoder.encode(createAdminDto.getPassword()))
					.apellidos(createAdminDto.getApellidos())
					.nombre(createAdminDto.getNombre())
					.edad(createAdminDto.getEdad())
					.roles(roles)
					.avatar(avatar)
					.build();
					

			return this.repositorio.save(newAdmin);
		}
	
	
				

}
