package com.example.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.model.entities.Login;
import com.example.app.repositories.LoginRepository;

import jakarta.transaction.Transactional;

@Service
public class LoginService {
	
	
	@Autowired
	private LoginRepository lp;
	
	private Login login;
	
	public void validarLogin(String usuario, String senha) {
		
		if(login.getNomeUsuario().equals(usuario) && login.getSenha().equals(senha)) {
			System.out.println("Apresentar área logada");
		}
		else {
			System.out.println("Usuário não cadastrado");
		}
	}
	
	@Transactional
	public Login buscarPorId(Integer id) {
		Login login = lp.findById(id).get();
		return login;
		
		
		//@Transactional
//	public void excluirLogin(Integer id){
//		
//	}
	}
	
//	

}
