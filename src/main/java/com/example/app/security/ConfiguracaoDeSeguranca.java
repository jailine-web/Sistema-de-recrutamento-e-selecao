package com.example.app.security;

import java.util.Arrays;

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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.example.app.services.FiltroDeSeguranca;

@Configuration
@EnableWebSecurity
public class ConfiguracaoDeSeguranca {

	@Autowired
	private FiltroDeSeguranca filtroDeSeguranca;

	// Corrente de filtro(métodos de validação para cada uma das requisições) de
	// segurança
	@Bean
	 SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//				.authorizeHttpRequests(authorize -> authorize
//						.requestMatchers(HttpMethod.POST, "/hisig10/auth/cadastro").permitAll()
//						.requestMatchers(HttpMethod.POST, "/hisig10/auth/logar").permitAll()
//						.requestMatchers(HttpMethod.GET, "/hisig10/vagas").permitAll()
//						.requestMatchers(HttpMethod.POST, "/hisig10/usuarios/recrutadores").hasAnyRole("ADMIN")
//						.requestMatchers(HttpMethod.POST,"/hisig10/vagas").permitAll()
//						.anyRequest().authenticated())
//				
//				.addFilterBefore(filtroDeSeguranca, UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	@Bean
	 AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfig)
			throws Exception {
		return authenticationConfig.getAuthenticationManager();
	}

	@Bean
	 PasswordEncoder criptografarSenha() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	 CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedOrigin("*"); // Domínio do seu frontend
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT"));
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
	
	
}