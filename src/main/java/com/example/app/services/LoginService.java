package com.example.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.app.controller.excecao.Tratamentoexcecao;
import com.example.app.model.entities.Login;
import com.example.app.projection.LoginProjection;
import com.example.app.repositories.LoginRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;

@Service
public class LoginService {

	@Autowired
	private LoginRepository lr;

	private final PasswordEncoder senhaEncriptada;

	public LoginService(LoginRepository loginRepositorio, PasswordEncoder senhaEncriptada) {
		this.lr = loginRepositorio;
		this.senhaEncriptada = senhaEncriptada;

	}

	@Transactional
	public List<Login> buscarTodos() {
		List<Login> lista = lr.findAll();
		return lista;
	}

	@Transactional
	public Login buscarPorId(Integer id) {
		Login login = lr.findById(id).get();
		return login;

	}

	@Transactional
	public void excluirLogin(Integer id) {
		try {

			buscarPorId(id);
			lr.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
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

		login.setSenha(senhaEncriptada.encode(login.getSenha()));
		return lr.save(login);
	}

	@Transactional
	public Boolean validarSenha(String email, String usuario, String senha) {

		// Consulta para retornar o login pelo campo usuario
		Optional<LoginProjection> loginOpt = lr.buscarUsuarios(usuario);
		
		LoginProjection login = loginOpt.get();

		if (login.getUsuario().isEmpty()) {
			return false;
		}
		if(!login.getEmail().equals(email)) {
			return false;
		}

		// Verifica se a senha passada por parâmetro é igual a que está armazenada no BD
		boolean valido = senhaEncriptada.matches(senha, login.getSenha());

		return valido;
	}

}
