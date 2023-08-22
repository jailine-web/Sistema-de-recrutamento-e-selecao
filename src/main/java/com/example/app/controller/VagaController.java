package com.example.app.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.app.DTO.PerguntaDTO;
import com.example.app.DTO.QuestionarioDTO;
import com.example.app.model.entities.Pergunta;
import com.example.app.model.entities.Questionario;
import com.example.app.model.entities.Vaga;
import com.example.app.repositories.QuestionarioRepository;
import com.example.app.services.VagaService;

@RestController
@RequestMapping(value="/hisig10/vagas")
public class VagaController {
	
	@Autowired
	private VagaService vs;
	
	@Autowired
	private QuestionarioRepository questionarioRepository;
	
	@GetMapping
	public List<Vaga> buscarTodos(){
		List<Vaga> listaVagas = vs.buscarTodos();
		
		return listaVagas;
	}
	
	@PostMapping
	public ResponseEntity<Vaga> inserirVaga(@RequestBody Vaga vaga){
		vaga = vs.inserirVaga(vaga);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vaga.getId()).toUri();
		return ResponseEntity.created(uri).body(vaga);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Vaga> buscarPorId(@PathVariable Integer id) {
		Vaga v = vs.buscarPorId(id);
		return ResponseEntity.ok().body(v);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Vaga> atualizarVaga(@PathVariable Integer id, @RequestBody Vaga vaga){
		vaga = vs.atualizarVaga(id, vaga);
		return ResponseEntity.ok().body(vaga);	
	}
	
	@PostMapping("/questionarios/{vagaId}")
	public ResponseEntity<?> criarQuestionarioParaVaga(@RequestBody QuestionarioDTO questionarioDTO, @PathVariable Integer vagaId){
		try {
			Vaga vaga = vs.buscarPorId(vagaId);
			
			Questionario questionario = new Questionario();
			questionario.setTitulo(questionarioDTO.getTitulo());
			
			List<Pergunta> perguntas = new ArrayList<>();
			for (PerguntaDTO perguntaDTO : questionarioDTO.getPerguntas()) {
				Pergunta pergunta = new Pergunta();
				pergunta.setTexto(perguntaDTO.getTexto());
				pergunta.setOpcoesResposta(perguntaDTO.getOpcoesResposta());
				pergunta.setQuestionario(questionario);
				perguntas.add(pergunta);
			}
			questionario.setPerguntas(perguntas);
			questionario.setVaga(vaga);
			
			questionarioRepository.save(questionario);
			
			return ResponseEntity.status(HttpStatus.CREATED).build();
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/questionarios")
	public ResponseEntity<List<Questionario>> buscarQuestionariosDeTodasVagas() {
		List<Questionario> questionarios = questionarioRepository.findAll();
		return ResponseEntity.ok().body(questionarios);
	}
	
	@PutMapping(value ="{id}/fecharvaga")
	public ResponseEntity<Void> FecharVaga(@PathVariable Integer id) {
		Vaga v1 = vs.fecharVaga(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value="/{id}/vagaativa")
	public ResponseEntity<Long> vagaAtiva(@PathVariable Integer id) {
		Long diasAtiva = vs.vagaAtiva(id);
		return ResponseEntity.ok().body(diasAtiva);
	}
}
