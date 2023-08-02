package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//os parâmetros inseridos entre parênteses não executa a classe definida abaixo (.class), 
//evitando o aviso "não autorizado" do spring security no postman.
@SpringBootApplication(exclude= {SecurityAutoConfiguration.class})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public PasswordEncoder getSenhaCodada() {
		BCryptPasswordEncoder encriptar = new BCryptPasswordEncoder();
		return encriptar;
	}
}