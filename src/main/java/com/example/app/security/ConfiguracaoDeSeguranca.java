package com.example.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.app.services.FiltroDeSeguranca;

@Configuration
@EnableWebSecurity
public class ConfiguracaoDeSeguranca {

	@Autowired
	private FiltroDeSeguranca filtroDeSeguranca;
	
	@Bean
	//corrente de filtro(métodos de validação para  cada uma das requisições) de segurança
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.csrf(csrf->csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			/*	.authorizeHttpRequests(authorize-> authorize
						.requestMatchers(HttpMethod.POST, "/hisig10/auth/logar").permitAll()
						.requestMatchers(HttpMethod.POST, "/hisig10/auth/cadastro").permitAll()
						.requestMatchers(HttpMethod.POST, "/hisig10/usuarios/recrutadores").hasAnyRole("ADMIN")
						.anyRequest().authenticated()
				)
				.addFilterBefore(filtroDeSeguranca, UsernamePasswordAuthenticationFilter.class)
			*/
				.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfig) throws Exception {
		return authenticationConfig.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder criptografarSenha() {
		return new BCryptPasswordEncoder();
	}
}

