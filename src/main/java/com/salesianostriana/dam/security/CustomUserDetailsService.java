package com.salesianostriana.dam.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.model.Admin;
import com.salesianostriana.dam.model.Cliente;
import com.salesianostriana.dam.model.Gerente;
import com.salesianostriana.dam.service.AdminService;
import com.salesianostriana.dam.service.ClienteService;
import com.salesianostriana.dam.service.GerenteService;

import lombok.RequiredArgsConstructor;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private ClienteService clienteServicio;
	
	@Autowired
	private GerenteService gerenteServicio;
	
	@Autowired
	private final AdminService adminServicio;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User usuario;
		
		if(gerenteServicio.findFirstByEmail(username) != null) {
			Gerente user = gerenteServicio.findFirstByEmail(username);
			System.out.println(user);
			// System.out.println(user);
			
			usuario = new User(user.getUsername(), user.getPassword(), user.getAuthorities());

			return usuario; 
		} else if(adminServicio.findFirstByEmail(username) != null) {
			Admin user = adminServicio.findFirstByEmail(username);
			System.out.println(user);
			
			usuario = new User(user.getUsername(), user.getPassword(), user.getAuthorities());
			
			return usuario;
		} else {
			Cliente user = clienteServicio.findFirstByEmail(username);
		
			usuario = new User(user.getUsername(), user.getPassword(), user.getAuthorities());
		
			return usuario;
		}

	}
}
