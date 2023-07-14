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

import com.example.app.model.entities.Candidato;
import com.example.app.projection.CandidaturasCandidatoProjection;
import com.example.app.repositories.VagaRepository;
import com.example.app.services.CandidatoService;

@RestController
@RequestMapping(value="/usuarios/candidatos")
public class CandidatoController {

	@Autowired
	private CandidatoService cs;
	
	@Autowired
	private VagaRepository vr;
	
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
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Candidato> atualizarCandidato(@PathVariable Integer id, @RequestBody Candidato candidato) {
		candidato = cs.atualizarCandidato(id, candidato);
		return ResponseEntity.ok().body(candidato);
	}
	
	@GetMapping(value="/{idCand}/candidaturas")
	public List<CandidaturasCandidatoProjection> buscarCandidaturas(@PathVariable Integer idCand){
		List<CandidaturasCandidatoProjection> vagas = vr.buscarCandidaturas(idCand);
		return vagas;
	}
}
