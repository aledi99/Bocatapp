package com.salesianostriana.dam.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.model.Admin;
import com.salesianostriana.dam.model.Cliente;
import com.salesianostriana.dam.model.Gerente;
import com.salesianostriana.dam.model.Usuario;
import com.salesianostriana.dam.service.AdminService;
import com.salesianostriana.dam.service.ClienteService;
import com.salesianostriana.dam.service.GerenteService;

import lombok.RequiredArgsConstructor;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final ClienteService clienteServicio;
	private final GerenteService gerenteServicio;
	private final AdminService adminServicio;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if(gerenteServicio.findFirstByEmail(username) != null) {
			Gerente user = gerenteServicio.findFirstByEmail(username);
			System.out.println(user);
			// System.out.println(user);
//			List<GrantedAuthority> authority = (List<GrantedAuthority>) user.getAuthorities();
//			//return new User(user.getUsername(), user.getPassword(), authority);
			return new User(user.getUsername(), user.getPassword(), user.getAuthorities());
		}
		
		if(adminServicio.findFirstByEmail(username) != null) {
			Admin user = adminServicio.findFirstByEmail(username);
			System.out.println(user);
			// System.out.println(user);
//			List<GrantedAuthority> authority = (List<GrantedAuthority>) user.getAuthorities();
//			//return new User(user.getUsername(), user.getPassword(), authority);
			return new User(user.getUsername(), user.getPassword(), user.getAuthorities());
		}

		Cliente user = clienteServicio.findFirstByEmail(username);
		System.out.println(user);
		// System.out.println(user);
//		List<GrantedAuthority> authority = (List<GrantedAuthority>) user.getAuthorities();
//		//return new User(user.getUsername(), user.getPassword(), authority);
		return new User(user.getUsername(), user.getPassword(), user.getAuthorities());
	}
}
