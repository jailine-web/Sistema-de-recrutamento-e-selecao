package com.example.app.services;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.app.controller.excecao.Tratamentoexcecao;
import com.example.app.model.entities.Login;
import com.example.app.model.entities.Recrutador;
import com.example.app.repositories.LoginRepository;
import com.example.app.repositories.RecrutadorRepository;
import com.example.app.utils.Util;

import jakarta.transaction.Transactional;

@Service
public class LoginService {
	
	@Autowired
	private LoginRepository lr;
	
	@Autowired
	private RecrutadorRepository rr;
	
	private Login login;
	
	@Transactional
	public List<Login> buscarTodos(){
		List<Login> lista = lr.findAll();
		return lista;
	}
	
	@Transactional
	public Login buscarPorId(Integer id) {
		Login login = lr.findById(id).get();
		return login;
		
	}
	
	@Transactional
	public void excluirLogin(Integer id){
		try {
			
			buscarPorId(id);
			lr.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new Tratamentoexcecao("Login não encontrado");
		}
	}
	
	@Transactional
	public Login atualizarLogin(Integer id, Login login) {
		
		Login loginAtualizado = lr.getReferenceById(id);
		loginAtualizado.setUsuario(login.getUsuario());
		loginAtualizado.setSenha(login.getSenha());
		return lr.save(loginAtualizado);
	}
	
	@Transactional
	public Login inserirLogin(Login login) {
		
		Login l = lr.save(login);
		return lr.save(l);
	}
	
	@Transactional
	public void validar(Login login) throws Exception{
		try {
			if(lr.findByEmail(login.getEmail()) != null) {
				throw new Tratamentoexcecao("O email já está cadastrado para este email: "+ login.getEmail());
			}
			
			login.setSenha(Util.md5(login.getSenha()));
		}
		catch(Exception e) {
			throw new Tratamentoexcecao("Erro na criptografia da senha");
		}
		lr.save(login);
	}

}
