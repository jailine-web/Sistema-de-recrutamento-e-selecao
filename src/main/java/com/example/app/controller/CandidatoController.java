package com.example.app.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.app.DTO.CandidatoReduzido;
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
	public ResponseEntity<Candidato> buscarPorId(@PathVariable Integer id) {
		
		Candidato idArmazenado = cs.buscarPorId(id);
		return ResponseEntity.ok().body(idArmazenado);
		
	}
	
	@PostMapping
	public ResponseEntity<Candidato> inserirCandidato(@RequestBody Candidato candidato){
		
		candidato = cs.inserirCandidato(candidato);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(candidato.getId()).toUri();
		return ResponseEntity.created(uri).body(candidato);
		
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Candidato> excluirCandidato(@PathVariable Integer id) {
		
		cs.excluirCandidato(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value="/{idVagas}/candidatura")
	public List<CandidatoReduzido> buscarCandidatos(@PathVariable Integer idVagas){
		List<CandidatoReduzido> listaCandidatos = cs.buscarCandidatos(idVagas);
		return listaCandidatos;
	}
}
