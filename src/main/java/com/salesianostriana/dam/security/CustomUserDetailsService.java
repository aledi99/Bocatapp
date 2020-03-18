package com.salesianostriana.dam.security;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.model.Usuario;
import com.salesianostriana.dam.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UsuarioService usuarioServicio;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// System.out.println("ENTRA EN EL METODO LOAD USER");
		Usuario user = usuarioServicio.findFirstByEmail(username);
		System.out.println(user);
		// System.out.println(user);
//		List<GrantedAuthority> authority = (List<GrantedAuthority>) user.getAuthorities();
//		//return new User(user.getUsername(), user.getPassword(), authority);
		return new User(user.getUsername(), user.getPassword(), user.getAuthorities());
	}
}
