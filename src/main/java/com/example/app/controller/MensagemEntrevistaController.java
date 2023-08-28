package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.model.entities.MensagemEntrevista;
import com.example.app.repositories.MensagemEntrevistaRepository;
import com.example.app.repositories.MensagemRepository;

@RestController
@RequestMapping(value="/hisig10/mensagemEntrevista")

public class MensagemEntrevistaController {

	@Autowired
	private MensagemEntrevistaRepository mensagemRepository;
	
	@PostMapping
	public ResponseEntity<MensagemEntrevista> cadastrarMensagem(@RequestBody MensagemEntrevista me) {
		 MensagemEntrevista mensagem = mensagemRepository.save(me);
		return ResponseEntity.ok().body(mensagem);
	}
}
