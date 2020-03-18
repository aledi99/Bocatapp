package com.salesianostriana.dam.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import com.salesianostriana.dam.error.CustomAccessDeniedHandler;
import com.salesianostriana.dam.error.CustomAuthenticationEntryPoint;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableResourceServer
@RequiredArgsConstructor
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{
	private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
       resources.resourceId("api");
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
        
        http.headers().frameOptions().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/h2-console/**").permitAll()
				.antMatchers("/api/**").authenticated()
				.and().exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint)
				.accessDeniedHandler(new CustomAccessDeniedHandler());
	
	}

}
