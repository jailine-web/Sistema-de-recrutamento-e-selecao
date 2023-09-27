package com.example.app.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.app.model.entities.Vaga;
import com.example.app.services.VagaService;
import com.example.app.utils.EstadoVaga;

@RestController
@RequestMapping(value = "/hisig10/vagas")
public class VagaController {

	@Autowired
	private VagaService vs;

	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<Vaga>> buscarTodos() {
		List<Vaga> listaVagas = vs.buscarTodos();
		return ResponseEntity.ok().body(listaVagas);
	}

	@CrossOrigin
	@PostMapping
	public ResponseEntity<Vaga> inserirVaga(@RequestBody Vaga vaga) {
		vaga = vs.inserirVaga(vaga);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vaga.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Vaga> buscarPorId(@PathVariable Integer id) {
		Vaga v = vs.buscarPorId(id);
		return ResponseEntity.ok().body(v);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Vaga> atualizarVaga(@PathVariable Integer id, @RequestBody Vaga vaga) {
		vaga = vs.atualizarVaga(id, vaga);
		return ResponseEntity.ok().body(vaga);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> excluirVaga(@PathVariable Integer id) {

		vs.excluirVaga(id);
		return ResponseEntity.ok().build();

	}

	@PutMapping(value = "{id}/fecharvaga")
	public ResponseEntity<Void> FecharVaga(@PathVariable Integer id) {
		Vaga v1 = vs.fecharVaga(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/{id}/vagaativa")
	public ResponseEntity<Long> vagaAtiva(@PathVariable Integer id) {
		Long diasAtiva = vs.vagaAtiva(id);
		return ResponseEntity.ok().body(diasAtiva);
	}

}
