package com.example.app.model.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.app.utils.Regras;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class Login implements UserDetails, Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	@Column(unique = true)
	private String usuario;
	
	@NotNull
	private String senha;
	
	@Enumerated(EnumType.STRING)
	private Regras regras;
	
	public Login() {
		
	}
	
	public Login(@NotBlank String email, @NotBlank String usuario, String senha, Regras regras) {
		this.email = email;
		this.usuario = usuario;
		this.senha = senha;
		this.regras = regras;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(this.regras == Regras.ADMIN) return java.util.List.of(new SimpleGrantedAuthority("REGRA_ADMIN")
				,new SimpleGrantedAuthority("REGRA_ESTAGIARIOS"), new SimpleGrantedAuthority("REGRA_CANDIDATOS"));
		else return java.util.List.of(new SimpleGrantedAuthority("REGRA_CANDIDATOS"));
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Regras getRegras() {
		return regras;
	}

	public void setRegras(Regras regras) {
		this.regras = regras;
	}
	
	@Override
	public String getUsername() {
		return this.getUsuario();
		
	}
	
	@Override
	public String getPassword() {
		return this.getSenha();
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
	public int hashCode() {
		return Objects.hash(senha, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Login other = (Login) obj;
		return Objects.equals(senha, other.senha) && Objects.equals(usuario, other.usuario);
	}
	
	
	
}