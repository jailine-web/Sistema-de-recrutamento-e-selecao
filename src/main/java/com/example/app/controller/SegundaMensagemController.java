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

import com.example.app.model.entities.MensagemEntrevista;
import com.example.app.services.SegundaMensagemService;

@RestController
@RequestMapping(value="/hisig10/segundaMensagem")
public class SegundaMensagemController {
	
	@Autowired
	private SegundaMensagemService segundaMensagemService;

	@PostMapping
	public ResponseEntity<MensagemEntrevista> cadastrarSegundaMensagem(@RequestBody MensagemEntrevista segundaMensagem) {
		segundaMensagem = segundaMensagemService.cadastrarSegundaMensagem(segundaMensagem);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<MensagemEntrevista> atualizarSegundaMensagem(@PathVariable Integer id, 
			@RequestBody MensagemEntrevista segundaMensagem) {
		segundaMensagem = segundaMensagemService.atualizarSegundaMensagem(id, segundaMensagem);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value ="/{id}")
	public ResponseEntity<MensagemEntrevista> buscarSegundaMensagemPorId(@PathVariable Integer id){
		MensagemEntrevista segundaMensagem = segundaMensagemService.buscarSegundaMensagemPorId(id);
		return ResponseEntity.ok().body(segundaMensagem);
		
	}
	
	@GetMapping
	public ResponseEntity<List<MensagemEntrevista>> retornarSegundaMensagens() {
		List<MensagemEntrevista> mensagens = segundaMensagemService.getMensagens();
		return ResponseEntity.ok().body(mensagens);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<MensagemEntrevista> deletarSegundaMensagem(@PathVariable Integer id) {
		segundaMensagemService.excluirConvitePorId(id);
		return ResponseEntity.noContent().build();
	}
}
