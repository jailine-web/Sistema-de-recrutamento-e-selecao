package com.example.app.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.model.entities.Entrevista;
import com.example.app.model.entities.Lembrete;
import com.example.app.model.entities.MensagemEntrevista;
import com.example.app.repositories.EntrevistaRepository;
import com.example.app.services.EntrevistaService;
import com.example.app.services.LembreteService;

@RestController
@RequestMapping(value="/hisig10/entrevista")
public class EntrevistaController {
	
	@Autowired
	private EntrevistaService entrevistaService;
	
	@Autowired
	private EntrevistaRepository entrevistaRepository;
	
	@Autowired
	private LembreteService lembreteService;
	
	@PostMapping
	public ResponseEntity<Entrevista> inserirEntrevista(Entrevista entrevista){
		entrevista = entrevistaService.inserirEntrevista(entrevista);
		return ResponseEntity.ok().body(entrevista);
	}
	
	
	@PostMapping(value="/segunda_mensagem")
	public ResponseEntity<MensagemEntrevista> cadastrarMensagem(@RequestBody MensagemEntrevista mensagem) {
		mensagem = entrevistaService.enviarSegundaMensagem(mensagem);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping(value="/lembrete")
	public ResponseEntity<Lembrete> inserirLembrete(@RequestBody Lembrete lembrete){
		lembrete = lembreteService.inserirLembrete(lembrete);
		return ResponseEntity.ok().build();
	}
	

	@GetMapping(value="/{id}")
	public long enviarLembrete(@PathVariable Integer id) {
		
		Entrevista entrevista = entrevistaRepository.getReferenceById(id);
		
		LocalDateTime l = entrevista.getData();
		
		long horas = ChronoUnit.HOURS.between(l, LocalDateTime.now());
		
		if(horas <= 2) {
			System.out.println("Envie mensagem");
		}else {
			System.out.println("Não é hora ainda");
		}
		return horas;
	}
	
	
	
	

}
