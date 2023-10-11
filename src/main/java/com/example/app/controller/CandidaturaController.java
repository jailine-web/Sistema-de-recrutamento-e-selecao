package com.example.app.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.model.entities.Candidato;
import com.example.app.model.entities.Candidatura;
import com.example.app.model.entities.CandidaturaRelatorio;
import com.example.app.model.entities.Mensagem;
import com.example.app.model.entities.Vaga;
import com.example.app.projection.CurriculoAvaliadoProjection;
import com.example.app.repositories.CandidatoRepository;
import com.example.app.repositories.CandidaturaRelatorioRepository;
import com.example.app.repositories.CandidaturaRepository;
import com.example.app.repositories.MensagemRepository;
import com.example.app.repositories.VagaRepository;
import com.example.app.services.CandidaturaService;
import com.example.app.services.NotificacaoService;
import com.example.app.utils.EstadoInscricao;
import com.example.app.utils.Notas;
import com.example.app.utils.StatusCurriculoAvaliado;

@RestController
@RequestMapping(value="/hisig10/candidaturas")
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
	
	@Autowired
	private CandidaturaRelatorioRepository relatorioRepository;
	
	@Autowired
	private CandidaturaService candidaturaService;
	

	@PostMapping
	public ResponseEntity<?> criarCandidatura(@RequestBody Candidatura candidatura) {

		// Requisição JSON com "vaga":{"id": 1}, "candidato":{"id": 2}, "estado": "SELECIONADO"
		List<Candidatura> candidaturasExistentes = candidaturaRepository.findByCandidatoAndVaga(
				candidatura.getCandidato(), candidatura.getVaga());
		if (!candidaturasExistentes.isEmpty()) {
			return ResponseEntity.badRequest().body("Este candidato já se candidatou para uma vaga");
		}
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
		candidatura.setVaga(vaga);
		candidatura.setCandidato(candidato);
		candidatura.setDataInscricao(LocalDateTime.now());

		candidaturaRepository.save(candidatura);
		return ResponseEntity.status(HttpStatus.CREATED).body(candidatura);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> retornarCandidatura(@PathVariable Long id) {
		
		Candidatura candidatura = candidaturaRepository.findById(id).orElse(null);		
		if (candidatura == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(candidatura);
	}
	
	@GetMapping(value="/adequadas")
	public List<Candidatura> adequadas(){
		
		return candidaturaService.candidaturasSelecionadas();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluirCandidatura(@PathVariable Long id) {
		Candidatura candidatura = candidaturaRepository.findById(id).orElse(null);
		if (candidatura == null) {
			return ResponseEntity.notFound().build();
		}
		candidaturaRepository.delete(candidatura);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/emitir_notificacoes")
	public ResponseEntity<Void> emitirNotificacoes() {
		List<Candidatura> candidaturas = candidaturaRepository.findAll();
		notificacaoService.gerarAlertas(candidaturas);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/mensagem_candidato")
	/*
	 * exemplo: "candidatoId": 1, "conteudo": "Esta é uma mensagem de teste"
	 */
	public ResponseEntity<?> enviarMensagemCandidato(@RequestBody Mensagem mensagem) {
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

	@PatchMapping(value="/{id}/selecionarEstado")
	public ResponseEntity<Candidatura> mudarEstadoCandidatura(@PathVariable Long id, @RequestBody Candidatura candidatura){
		candidatura = candidaturaService.mudarEstadoCandidatura(id, candidatura);
		return ResponseEntity.ok().build();
	}
	
	
	@GetMapping
	public List<Candidatura> listarCandidaturas() {
		return candidaturaRepository.findAll();
	}

	@GetMapping("/localizacao")
	/*
	 * http://localhost:8080/hisig10/candidaturas/localizacao?localizacao=São Paulo
	 */
	public List<Candidatura> listarCandidaturasPorLocalidade(@RequestParam("localizacao") String localizacao) {
		return candidaturaRepository.findByCandidatoLocalizacao(localizacao);
	}

	@GetMapping("/ordemalfabetica")
	public List<Candidatura> listarCandidaturaEmOrdemAlfabetica() {
		List<Candidatura> candidaturas = candidaturaRepository.findAllByOrderByCandidatoNomeAsc();
		return candidaturas;
	}

	@GetMapping("/datainscricao")
	public List<Candidatura> listarCandidaturasPorDataInscricao(
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime start,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime end) {
		
		
		List<Candidatura> candidaturas = candidaturaRepository.findByDataInscricaoBetween(start, end);
		return candidaturas;
		
	}

	@GetMapping("/relatorio")
	public ResponseEntity<List<CandidaturaRelatorio>> gerarRelatorioCandidaturasAdequadas() {
		
		List<Candidatura> candidaturasSelecionadas = candidaturaRepository.findByEstado(EstadoInscricao.SELECIONADO);

		List<CandidaturaRelatorio> relatorioDTOs = new ArrayList<>();
		for (Candidatura candidatura : candidaturasSelecionadas) {
			CandidaturaRelatorio relatorio = new CandidaturaRelatorio();
			relatorio.setCandidaturaId(candidatura.getId());
			relatorio.setVagaId(candidatura.getVaga().getId().longValue());
			relatorio.setVagaTitulo(candidatura.getVaga().getTitulo());
			relatorio.setCandidatoId(candidatura.getCandidato().getId().longValue());
			relatorio.setCandidatoNome(candidatura.getCandidato().getNome());
			relatorio.setEstadoInscricao(candidatura.getEstado());
			
			relatorioRepository.save(relatorio);
			relatorioDTOs.add(relatorio);
		}
		return ResponseEntity.ok(relatorioDTOs);
	}
	
	@GetMapping("/relatorio/{id}")
	public ResponseEntity<CandidaturaRelatorio> getRelatorioPorId(@PathVariable Long id){
		Optional<CandidaturaRelatorio> relatorio = relatorioRepository.findById(id);
		if (relatorio.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(relatorio.get());
	}

	@GetMapping("/inadequadas")
	public ResponseEntity<List<CandidaturaRelatorio>> getCandidaturasInadequadas() {
		
		List<Candidatura> candidaturasRejeitadas = candidaturaRepository.findByEstado(EstadoInscricao.INADEQUADO);

		List<CandidaturaRelatorio> relatorioDTOs = new ArrayList<>();
		for (Candidatura candidatura : candidaturasRejeitadas) {
			CandidaturaRelatorio relatorio = new CandidaturaRelatorio();
			relatorio.setCandidaturaId(candidatura.getId());
			relatorio.setVagaId(candidatura.getVaga().getId().longValue());
			relatorio.setVagaTitulo(candidatura.getVaga().getTitulo());
			relatorio.setCandidatoId(candidatura.getCandidato().getId().longValue());
			relatorio.setCandidatoNome(candidatura.getCandidato().getNome());
			relatorio .setEstadoInscricao(candidatura.getEstado());
			
			relatorioRepository.save(relatorio);
			relatorioDTOs.add(relatorio);
		}
		return ResponseEntity.ok(relatorioDTOs);
	}

	@DeleteMapping("/inadequadas/{id}")
	public ResponseEntity<?> excluirCandidaturaInadequada(@PathVariable Long id) {
		Candidatura candidatura = candidaturaRepository.findById(id).orElse(null);

		if (candidatura == null) {
			return ResponseEntity.notFound().build();
		}

		if (candidatura.getEstado() != EstadoInscricao.INADEQUADO) {
			return ResponseEntity.badRequest().body("Apenas candidaturas rejeitadas podem ser excluídas!");
		}

		candidaturaRepository.delete(candidatura);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/talvez")
	public ResponseEntity<List<CandidaturaRelatorio>> getCandidaturasTalvez(){
		
		List<Candidatura> candidaturasTalvez = candidaturaRepository.findByEstado(EstadoInscricao.TALVEZ);
		
		List<CandidaturaRelatorio> relatorioDTOs = new ArrayList<>();
		for (Candidatura candidatura : candidaturasTalvez) {
			CandidaturaRelatorio relatorio = new CandidaturaRelatorio();
			relatorio.setCandidaturaId(candidatura.getId());
			relatorio.setVagaId(candidatura.getVaga().getId().longValue());
			relatorio.setVagaTitulo(candidatura.getVaga().getTitulo());
			relatorio.setCandidatoId(candidatura.getCandidato().getId().longValue());
			relatorio.setCandidatoNome(candidatura.getCandidato().getNome());
			relatorio.setEstadoInscricao(candidatura.getEstado());
			
			relatorioRepository.save(relatorio);
			relatorioDTOs.add(relatorio);
		}
		return ResponseEntity.ok(relatorioDTOs);
	}
	
	@GetMapping("/curriculos/avaliados")
	public ResponseEntity<List<CurriculoAvaliadoProjection>> obterCurriculosAvaliados(){
		
		List<CurriculoAvaliadoProjection> curriculosAvaliados = candidatoRepository.
				findByCurriculoAvaliado(StatusCurriculoAvaliado.AVALIADO);
		
		if (curriculosAvaliados.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(curriculosAvaliados);
	}
	
}
