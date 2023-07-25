package com.example.app.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.app.model.entities.Login;
import com.example.app.services.LoginService;

@RestController
@RequestMapping(value="/login")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@GetMapping
	public List<Login>buscarTodos(){
		return loginService.buscarTodos();
	}
	
	@GetMapping(value ="/{id}")
	public ResponseEntity<Login> buscarPorId(@PathVariable Integer id) {
		Login login = loginService.buscarPorId(id);
		return ResponseEntity.ok().body(login);
	}
	
	@DeleteMapping(value ="/{id}")
	public ResponseEntity<Login> excluirLogin(@PathVariable Integer id){
		loginService.excluirLogin(id);
		return ResponseEntity.noContent().build() ;
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Login> atualizarLogin(@PathVariable Integer id, @RequestBody Login login) {
		login = loginService.atualizarLogin(id, login);
		return ResponseEntity.ok().body(login);
		
	}
	
	@PostMapping
	public ResponseEntity<Login> inserirLogin(@RequestBody Login login){
		loginService.inserirLogin(login);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(login.getId()).toUri();
		return ResponseEntity.created(uri).body(login);
	}
	
}
