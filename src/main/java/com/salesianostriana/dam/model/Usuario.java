package com.salesianostriana.dam.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public abstract class Usuario implements UserDetails {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nombre;
	private String username;
	private String apellidos;
	private Integer edad;
	@NotEmpty
	@Email
	private String email;
	@JsonIgnore
    @ToString.Exclude
	private String password;
	
	private long[] favoritos;
	
	@OneToOne
	private Ubicacion localizacion;
	
	@OneToOne
	private Avatar avatar;
	
	@NotNull
    @ElementCollection(fetch = FetchType.EAGER)
	private Set<Role> roles;
	
	@CreatedDate
	private Date fechaCreacion;
	

	@Builder.Default
	private LocalDateTime lastPasswordChangedAt = LocalDateTime.now();
	
	/*@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream().map(x->new SimpleGrantedAuthority(x.getDescripcion())).collect(Collectors.toList());
	}*/
	
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
