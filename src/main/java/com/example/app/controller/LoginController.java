package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.model.entities.Login;
import com.example.app.services.LoginService;

@RestController
@RequestMapping(value="/login")
public class LoginController {

	@Autowired
	private LoginService loginservice;
	
	@GetMapping(value ="/{id}")
	public ResponseEntity<Login> buscarPorId(@PathVariable Integer id) {
		Login login = loginservice.buscarPorId(id);
		return ResponseEntity.ok().body(login);
	}
	
}
