package com.salesianostriana.dam.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import lombok.RequiredArgsConstructor;


@Configuration
//Habilitar el servidor de autorización
@EnableAuthorizationServer
@RequiredArgsConstructor
public class OAuthConfig extends AuthorizationServerConfigurerAdapter {

	private final AuthenticationManager authenticationManager;
	private final PasswordEncoder passwordEncoder;
	//private final UsuarioServicio usuarioServicio;
	private final UserDetailsService userDetailsService;

	
	//La anotación Value se usa para inyectar valores en campos de un bean
	@Value("${jwt.clientId:bocatapp-rule-5}")
	private String clientId;
	
	@Value("${jwt.client-secret:secret}")
	private String clientSecret;

	@Value("${jwt.signing-key:123}")
	private String jwtSigningKey;

	@Value("${jwt.accessTokenValidititySeconds:43200}")
	private int accessTokenValiditySeconds;

	@Value("${jwt.authorizedGrantTypes:password,authorization_code,refresh_token}")
	private String[] authorizedGrantTypes;

	@Value("${jwt.refreshTokenValiditySeconds:2592000}")
	private int refreshTokenValiditySeconds;
	
	
	//Configurar el acceso de cliente
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		//uso de almacenamiento en memoria. La otra alternativa en JDBC
		clients.inMemory()
               .withClient(clientId) //autenticación con clientID y clientsecret
               .secret(passwordEncoder.encode(clientSecret))
               .accessTokenValiditySeconds(accessTokenValiditySeconds) //validar token
               .refreshTokenValiditySeconds(refreshTokenValiditySeconds) //refrescar el token
               .authorizedGrantTypes(authorizedGrantTypes)
               .scopes("read", "write") //scopes
               .resourceIds("api"); //recursos accesibles para el cliente
	}
	
	//Configurar el authorization server endpoint
	 @Override
	 public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
		 endpoints
               .accessTokenConverter(accessTokenConverter())//usp de JSON WEB TOKEN
               .userDetailsService(userDetailsService) 
               .authenticationManager(authenticationManager);
		 //usamos UserDetailsService y autenticationManager para hacer la autentificación
	 }

	 @Bean
	 JwtAccessTokenConverter accessTokenConverter() {
       JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
       return converter;
	 }


	
}
