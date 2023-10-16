package com.example.app.controller;

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

import com.example.app.model.entities.Entrevista;
import com.example.app.repositories.SegundaMensagemRepository;
import com.example.app.services.EntrevistaService;

@RestController
@RequestMapping(value="/hisig10/entrevista")
public class EntrevistaController {
	
	@Autowired
	private EntrevistaService entrevistaService;
	
	@Autowired
	private SegundaMensagemRepository mensagemEntrevistaRepository;
	

	@PostMapping
	public ResponseEntity<Entrevista> inserirEntrevista(@RequestBody Entrevista entrevista){
		entrevista = entrevistaService.inserirEntrevista(entrevista);
		return ResponseEntity.ok().body(entrevista);
	}

	@PutMapping(value="/{id}")
	public ResponseEntity<Entrevista> atualizarEntrevista(@PathVariable Integer id, @RequestBody Entrevista entrevista){
		entrevistaService.atualizarEntrevista(id, entrevista);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> excluirEntrevista(@PathVariable Integer id){
		entrevistaService.excluirEntrevista(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<Entrevista>> buscarEntrevistas(){
		List<Entrevista> entrevista = entrevistaService.getEntrevistas();
		return ResponseEntity.ok().body(entrevista);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Entrevista> buscarEntrevistaPorId(Integer id){
		entrevistaService.buscarEntrevistaPorId(id);
		return ResponseEntity.ok().build();
	}
	
}
