package com.example.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.model.entities.Vaga;
import com.example.app.services.VagaService;

@RestController
@RequestMapping(value="/vagas")
public class VagaController {
	
	@Autowired
	private VagaService vs;
	
	@GetMapping
	public List<Vaga> buscarTodos(){
		List<Vaga> listaVagas = vs.buscarTodos();
		
		return listaVagas;
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Vaga> buscarPorId(@PathVariable Integer id) {
		Vaga v = vs.buscarPorId(id);
		return ResponseEntity.ok().body(v);
	}
	
}
