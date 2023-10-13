package com.example.app.controller;

import java.net.URI;
import java.util.ArrayList;
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

import com.example.app.model.entities.Candidatura;
import com.example.app.model.entities.CandidaturaRelatorio;
import com.example.app.model.entities.Recrutador;
import com.example.app.projection.CandidaturasProjection;
import com.example.app.repositories.CandidatoRepository;
import com.example.app.repositories.CandidaturaRelatorioRepository;
import com.example.app.repositories.CandidaturaRepository;
import com.example.app.services.RecrutadorService;
import com.example.app.utils.EstadoInscricao;

@RestController
@RequestMapping(value="/hisig10/usuarios/recrutadores")
public class RecrutadorController {

	@Autowired
	private RecrutadorService rs;
	
	@Autowired
	private CandidatoRepository cr;
	
	@Autowired
	private CandidaturaRepository candidaturaRepository;
	
	@Autowired
	private CandidaturaRelatorioRepository relatorioRepository;
	
	@GetMapping
	public List<Recrutador>buscarTodos(){
		List<Recrutador> listaRecrutador = rs.buscarTodos();
		return listaRecrutador;
	}
	
	@PostMapping
	public ResponseEntity<Recrutador> inserirRecrutador(@RequestBody Recrutador recrutador) {
		recrutador = rs.inserirRecrutador(recrutador);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(recrutador.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping(value="/{idVagas}/candidaturas")
	public List<CandidaturasProjection> buscarCandidatos(@PathVariable Integer idVagas){
		List<CandidaturasProjection> listaCandidatos = cr.buscarCandidatosDaVaga(idVagas);
		return listaCandidatos;
	}
	
	@GetMapping(value= "/{id}")
	public ResponseEntity<Recrutador> buscarPorId(@PathVariable Integer id){
		Recrutador r = rs.buscarPorId(id);
		return ResponseEntity.ok().body(r);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Recrutador> atualizarRecrutador(@PathVariable Integer id, @RequestBody Recrutador recrutador){
		recrutador = rs.atualizar(id, recrutador);
		return ResponseEntity.ok().body(recrutador);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Recrutador> excluirRecrutador(@PathVariable Integer id){
		rs.excluirRecrutador(id);
		return ResponseEntity.noContent().build();
	}
	
}
