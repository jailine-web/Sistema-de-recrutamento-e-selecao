package com.example.app.services;

import com.example.app.model.entities.Login;

public class ValidaLogin {
	
	private Login login;

	public ValidaLogin(Login login) {
		super();
		this.login = login;
	}
	
	public void validarLogin(Login l) {
		
		if(login.equals(l)) {
			System.out.println("Apresentar área logada");
		}
		else {
			System.out.println("Usuário não cadastrado");
		}
	}

}
