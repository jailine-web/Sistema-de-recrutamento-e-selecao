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

import com.example.app.model.entities.Lembrete;
import com.example.app.services.LembreteService;

@RestController
@RequestMapping(value="hisig10/lembrete")
public class LembreteController {
	
	@Autowired
	private LembreteService lembreteService;

	@PostMapping
	public ResponseEntity<Lembrete> inserirLembrete(@RequestBody Lembrete lembrete){
		lembrete = lembreteService.inserirLembrete(lembrete);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> excluirLembrete(@PathVariable Integer id){
		lembreteService.excluirLembrete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Lembrete>atualizarLembrete(@PathVariable Integer id, @RequestBody Lembrete lembrete){
		lembrete = lembreteService.atualizarLembrete(id, lembrete);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value="/{id}/enviarLembrete")
	public long enviarLembrete(@PathVariable Integer id) {
		Long horas = lembreteService.enviarLembrete(id);
		return horas;
	}
	
	@GetMapping
	public ResponseEntity<List<Lembrete>> buscarLembretes(){
		List<Lembrete> lembretes = lembreteService.buscarLembretes();
		return ResponseEntity.ok().body(lembretes);
	}
	
	
}
