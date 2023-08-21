package com.example.app.security;

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
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@EnableWebSecurity
public class ConfiguracaoDeSeguranca {

	@Bean
	//corrente de filtro(métodos de validação para  cada uma das requisições) de segurança
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.csrf(csrf->csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				/*.authorizeHttpRequests(authorize-> authorize
						.requestMatchers(HttpMethod.POST, "/hisig10/auth/logar").permitAll()
						.requestMatchers(HttpMethod.POST, "/hisig10/auth/cadastro").permitAll()
						.requestMatchers(HttpMethod.POST, "/hisig10/usuarios/recrutador").hasAnyRole("ADMIN")
						.anyRequest().authenticated())
			*/	.build();
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

