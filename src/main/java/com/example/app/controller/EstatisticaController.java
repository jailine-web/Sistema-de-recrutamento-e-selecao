package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.repositories.EstatisticasRepository;
import com.example.app.repositories.VagaRepository;
import com.example.app.services.EstatisticasVagaService;

@RestController
@RequestMapping(value="/estatisticas")
public class EstatisticaController {

	@Autowired
	private EstatisticasVagaService evs;
	
	@Autowired
	private EstatisticasRepository er;
	
	@Autowired
	private VagaRepository vr;
	
	@GetMapping(value="/{id}/candidatos")
	public ResponseEntity<Long> buscarCandidaturasDaVaga(@PathVariable Integer id){
		Long candidaturas = evs.quantidadeDeCandidaturas(id);
		return ResponseEntity.ok().body(candidaturas);
	}
	
}
