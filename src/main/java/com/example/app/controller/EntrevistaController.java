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
import com.example.app.model.entities.Lembrete;
import com.example.app.model.entities.MensagemEntrevista;
import com.example.app.services.EntrevistaService;

@RestController
@RequestMapping(value="/hisig10/entrevista")
public class EntrevistaController {
	
	@Autowired
	private EntrevistaService entrevistaService;

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
		return ResponseEntity.ok().build();
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
	
	@PostMapping(value="/lembrete")
	public ResponseEntity<Lembrete> inserirLembrete(@RequestBody Lembrete lembrete){
		lembrete = entrevistaService.inserirLembrete(lembrete);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping(value="/lembrete/{id}")
	public ResponseEntity<Void> excluirLembrete(@PathVariable Integer id){
		entrevistaService.excluirLembrete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value="/lembrete/{id}")
	public ResponseEntity<Lembrete>atualizarLembrete(@PathVariable Integer id, @RequestBody Lembrete lembrete){
		lembrete = entrevistaService.atualizarLembrete(id, lembrete);
		return ResponseEntity.ok().build();
	}
	
	
	@GetMapping(value="/{id}/enviarLembrete")
	public long enviarLembrete(@PathVariable Integer id) {
		Long horas = entrevistaService.enviarLembrete(id);
		return horas;
	}
	
	@GetMapping(value="/lembretes")
	public ResponseEntity<List<Lembrete>> buscarLembretes(){
		List<Lembrete> lembretes = entrevistaService.buscarLembretes();
		return ResponseEntity.ok().build();
	}
	
	@PostMapping(value="/segunda_mensagem")
	public ResponseEntity<MensagemEntrevista> cadastrarMensagem(@RequestBody MensagemEntrevista mensagem) {
		mensagem = entrevistaService.inserirSegundaMensagem(mensagem);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping(value="/segunda_mensagem/{id}")
	public ResponseEntity<MensagemEntrevista> atualizarMensagem(@PathVariable Integer id, @RequestBody MensagemEntrevista mensagem){
		mensagem = entrevistaService.atualizarSegundaMensagem(id, mensagem);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping
	public ResponseEntity<Void> excluirMensagem(@PathVariable Integer id){
		entrevistaService.excluirSegundaMensagem(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value="/segunda_mensagem")
	public ResponseEntity<List<MensagemEntrevista>> buscarMensagens(){
		List<MensagemEntrevista> mensagens = entrevistaService.buscarSegundaMensagens();
		return ResponseEntity.ok().build();
	
	}
	
	@PostMapping(value="/convidar_candidato")
	public ResponseEntity<MensagemEntrevista> convidarCandidato(@RequestBody MensagemEntrevista convite) {
		convite = entrevistaService.convidarCandidato(convite);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping(value="/convidar_candidato/{id}")
	public ResponseEntity<MensagemEntrevista> atualizarConvite(@PathVariable Integer id, @RequestBody MensagemEntrevista convite) {
		convite = entrevistaService.atualizarConvite(id, convite);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value="/convidar_candidato")
	public ResponseEntity<List<MensagemEntrevista>> retornarConvites() {
		List<MensagemEntrevista> convites = entrevistaService.getConvites();
		return ResponseEntity.ok().body(convites);
	}
	
	@DeleteMapping(value="/convidar_candidato/{id}")
	public ResponseEntity<MensagemEntrevista> deletarConvitePorId(@PathVariable Integer id) {
		entrevistaService.excluirConvitePorId(id);
		return ResponseEntity.ok().build();
	}
}
