package com.salesianostriana.dam.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.salesianostriana.dam.error.CustomAccessDeniedHandler;
import com.salesianostriana.dam.error.CustomAuthenticationEntryPoint;


@Configuration
@EnableWebSecurity
//Esta anotación permite especificar la seguridad a nivel de clase o método. (preauthorize postauthorize)
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class ServerSecurityConfig extends WebSecurityConfigurerAdapter {
	private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

	private final UserDetailsService userDetailsService;

	public ServerSecurityConfig(CustomAuthenticationEntryPoint customAuthenticationEntryPoint,
			@Qualifier("usuarioServicio") UserDetailsService userDetailsService) {
		this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
		this.userDetailsService = userDetailsService;
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
        //http.csrf().disable();
        http.headers().frameOptions().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/h2-console/**").permitAll()
				.antMatchers("/api/**").authenticated().anyRequest()
				.authenticated()
				.and().exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint)
				.accessDeniedHandler(new CustomAccessDeniedHandler());
		
		// esta úlima línea es para gestionar errores propios
	}

}