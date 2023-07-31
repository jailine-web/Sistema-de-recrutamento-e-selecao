package com.example.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.model.entities.Candidatura;
import com.example.app.services.CandidaturaService;

@RestController
@RequestMapping("/candidaturas")
public class CandidaturaController {
	
	private final CandidaturaService cs;
	
	public CandidaturaController(CandidaturaService cs) {
		this.cs = cs;
	}
	
	@PostMapping
	public ResponseEntity<Candidatura> criarCandidatura(@RequestBody Candidatura candidatura){
		Candidatura novaCandidatura = cs.criarCandidatura(candidatura);
		return ResponseEntity.ok().body(novaCandidatura);
	}
}
