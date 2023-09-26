package com.example.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.DTO.PerguntaDTO;
import com.example.app.DTO.QuestionarioDTO;
import com.example.app.model.entities.Pergunta;
import com.example.app.model.entities.Questionario;
import com.example.app.model.entities.Vaga;
import com.example.app.repositories.QuestionarioRepository;
import com.example.app.services.VagaService;

@RestController
@RequestMapping(value="/hisig10/questionarios")
public class QuestionariosController {
	
	@Autowired
	private VagaService vs;
	
	@Autowired
	private QuestionarioRepository questionarioRepository;
	
		/*Inserir pergunta: {"titulo":"Inserindo", 
		 * "perguntas":[{"id":2,"questionario_id":2,"texto": "pergunta02"}]} */

	@PostMapping("/{vagaId}")
	public ResponseEntity<?> criarQuestionarioParaVaga(@PathVariable Integer vagaId, 
			@RequestBody QuestionarioDTO questionarioDTO){
		
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
	
	@GetMapping()
	public ResponseEntity<List<Questionario>> buscarQuestionariosDeTodasVagas() {
		List<Questionario> questionarios = questionarioRepository.findAll();
		return ResponseEntity.ok().body(questionarios);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarQuestionarioPorId(@PathVariable Long id){
		try {
			Optional<Questionario> questionarioOptional = questionarioRepository.findById(id);
			
			if (questionarioOptional.isPresent()) {
				Questionario questionario = questionarioOptional.get();
				return ResponseEntity.ok().body(questionario);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluirQuestionario(@PathVariable Long id){
		try {
			Optional<Questionario> questionarioOptional = questionarioRepository.findById(id);
			
			if (questionarioOptional.isPresent()) {
				questionarioRepository.delete(questionarioOptional.get());
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}
