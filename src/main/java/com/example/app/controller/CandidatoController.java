package com.example.app.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.model.entities.Candidato;
import com.example.app.services.CandidatoService;

@RestController
@RequestMapping(value="/candidatos")
public class CandidatoController {

	@Autowired
	private CandidatoService cs;
	
	@GetMapping
	public List<Candidato> buscarTodos(){
		List<Candidato> listaCandidatos = cs.buscarTodos();
		return listaCandidatos;
	}
	
	@GetMapping(value ="/{id}")
	public Candidato buscarPorId(@PathVariable Integer id) {
		
		Candidato idArmazenado = cs.buscarPorId(id);
		return idArmazenado;
	}
	
	
}
