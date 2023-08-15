package com.example.app.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.DTO.CandidatoReduzido;
import com.example.app.DTO.CandidaturaDTO;
import com.example.app.model.entities.Candidato;
import com.example.app.model.entities.Candidatura;
import com.example.app.model.entities.Mensagem;
import com.example.app.model.entities.Vaga;
import com.example.app.repositories.CandidatoRepository;
import com.example.app.repositories.CandidaturaRepository;
import com.example.app.repositories.MensagemRepository;
import com.example.app.repositories.VagaRepository;
import com.example.app.services.NotificacaoService;

@RestController
@RequestMapping("/hisig10/candidaturas")
public class CandidaturaController {
	
	@Autowired
	private CandidaturaRepository candidaturaRepository;
	
	@Autowired
	private VagaRepository vagaRepository;
	
	@Autowired
	private CandidatoRepository candidatoRepository;
	
	@Autowired
	private NotificacaoService notificacaoService;
	
	@Autowired
	private MensagemRepository mensagemRepository;
	
	@PostMapping
	public ResponseEntity<?> criarCandidatura(@RequestBody Candidatura candidatura){
		
		// Requisição JSON com "vaga":{"id": 1}, "candidato":{"id":2}
		if (candidatura.getVaga() == null || candidatura.getVaga().getId() == null) {
			return ResponseEntity.badRequest().body("O ID da vaga é obrigatório");
		}
		
		if (candidatura.getCandidato() == null || candidatura.getCandidato().getId() == null) {
			return ResponseEntity.badRequest().body("O ID do candidato é obrigatório");
		}
		
		Vaga vaga = vagaRepository.findById(candidatura.getVaga().getId()).orElse(null);
		if (vaga == null) {
			return ResponseEntity.badRequest().body("Vaga não encontrada");
		}
		
		Candidato candidato = candidatoRepository.findById(candidatura.getCandidato().getId()).orElse(null);
		if (candidato == null) {
			return ResponseEntity.badRequest().body("Candidato não encontrado");
		}
		
		CandidatoReduzido candidatoReduzido = new CandidatoReduzido();
		candidatoReduzido.setId(candidato.getId());
		candidatoReduzido.setNome(candidato.getNome());
		candidatoReduzido.setCurriculo(candidato.getCurriculo());
		
		CandidaturaDTO candidaturaDTO = new CandidaturaDTO();
		candidaturaDTO.setId(candidatura.getId());
		candidaturaDTO.setCandidato(candidatoReduzido);
		
		candidaturaRepository.save(candidatura);
		return ResponseEntity.status(HttpStatus.CREATED).body(candidaturaDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> retornarCandidatura(@PathVariable Long id){
		Candidatura candidatura = candidaturaRepository.findById(id).orElse(null);
		if (candidatura == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(candidatura);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluirCandidatura(@PathVariable Long id){
		Candidatura candidatura = candidaturaRepository.findById(id).orElse(null);
		if (candidatura == null) {
			return ResponseEntity.notFound().build();
		}
		candidaturaRepository.delete(candidatura);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/emitir_notificacoes")
	public ResponseEntity<Void> emitirNotificacoes(){
		List<Candidatura> candidaturas = candidaturaRepository.findAll();
		notificacaoService.gerarAlertas(candidaturas);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/mensagem_candidato")
/*		exemplo: 
    	"candidatoId": 1,
    	"conteudo": "Esta é uma mensagem de teste"*/
	public ResponseEntity<?> enviarMensagemCandidato(@RequestBody Mensagem mensagem){
		if (mensagem.getCandidatoId() == null) {
			return ResponseEntity.badRequest().body("O ID do candidato é obrigatório");
		}
		Candidato candidato = candidatoRepository.findById(mensagem.getCandidatoId().intValue()).orElse(null);
		if (candidato == null) {
			return ResponseEntity.notFound().build();
		}
		mensagem.setDataEnvio(LocalDateTime.now());
		
		Mensagem mensagemSalva = mensagemRepository.save(mensagem);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(mensagemSalva);
	}
	
	@GetMapping
	public List<Candidatura> listarCandidaturas(){
		return candidaturaRepository.findAll();
	}
	
	@GetMapping("/localizacao")
	/*
	 http://localhost:8080/hisig10/candidaturas/localizacao?localizacao=São Paulo
	 */
	public List<Candidatura> listarCandidaturasPorLocalidade(@RequestParam("localizacao") String localizacao){
			return candidaturaRepository.findByCandidatoLocalizacao(localizacao);
	}
	
	@GetMapping("/ordemalfabetica")
	public List<Candidatura> listarCandidaturaEmOrdemAlfabetica(){
		List<Candidatura> candidaturas = candidaturaRepository.findAllByOrderByCandidatoNomeAsc();
		return candidaturas;
	}
	
	@GetMapping("/datainscricao")
	public List<Candidatura> listarCandidaturasPorDataInscricao(
			@RequestParam @DateTimeFormat(pattern= "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime start,
			@RequestParam @DateTimeFormat(pattern= "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime end){
		List<Candidatura> candidaturas = candidaturaRepository.findByDataInscricaoBetween(start, end);
		return candidaturas;
	}
}
