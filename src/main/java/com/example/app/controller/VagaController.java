package com.example.app.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@PostMapping
	public ResponseEntity<Vaga> inserirVaga(@RequestBody Vaga vaga){
		vaga = vs.inserirVaga(vaga);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vaga.getId()).toUri();
		return ResponseEntity.created(uri).body(vaga);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Vaga> buscarPorId(@PathVariable Integer id) {
		Vaga v = vs.buscarPorId(id);
		return ResponseEntity.ok().body(v);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Vaga> atualizarVaga(@PathVariable Integer id, @RequestBody Vaga vaga){
		vaga = vs.atualizarVaga(id, vaga);
		return ResponseEntity.ok().body(vaga);
		
	}
}
