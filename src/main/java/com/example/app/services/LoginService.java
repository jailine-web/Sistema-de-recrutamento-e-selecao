package com.example.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.app.controller.excecao.Tratamentoexcecao;
import com.example.app.model.entities.Login;
import com.example.app.repositories.LoginRepository;

import jakarta.transaction.Transactional;

@Service
public class LoginService {
	
	@Autowired
	private LoginRepository lp;
	
	private Login login;
	
	@Transactional
	public List<Login> buscarTodos(){
		List<Login> lista = lp.findAll();
		return lista;
	}
	
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
		
	}
	
	@Transactional
	public void excluirLogin(Integer id){
		try {
			
			buscarPorId(id);
			lp.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new Tratamentoexcecao("Login não encontrado");
		}
	}
	
	@Transactional
	public Login atualizarLogin(Integer id, Login login) {
		
		Login loginAtualizado = lp.getReferenceById(id);
		loginAtualizado.setNomeUsuario(login.getNomeUsuario());
		loginAtualizado.setSenha(login.getSenha());
		return lp.save(loginAtualizado);
	}
	
	@Transactional
	public Login inserirLogin(Login login) {
		
		if(login.getNomeUsuario().equals("") || login.getSenha() == "") {
			throw new Tratamentoexcecao("Os campos usuário e login não podem ser vazios");
		}
		
		Login l = lp.save(login);
		
		return lp.save(l);
	}

}
