package com.example.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.model.entities.Entrevista;
import com.example.app.model.entities.MensagemEntrevista;
import com.example.app.repositories.EntrevistaRepository;
import com.example.app.repositories.MensagemEntrevistaRepository;

import jakarta.transaction.Transactional;

@Service
public class EntrevistaService {
	

	@Autowired
	private EntrevistaRepository entrevistaRepository;
	
	@Autowired
	private MensagemEntrevistaRepository mensagemEntrevistaRepository;
	
	@Transactional
	public MensagemEntrevista enviarSegundaMensagem(MensagemEntrevista mensagem) {
		MensagemEntrevista msg = mensagemEntrevistaRepository.save(mensagem);
		return msg;	
	}
	
	@Transactional
	public Entrevista inserirEntrevista(Entrevista entrevista) {
		Entrevista entrevistaSalva = entrevistaRepository.save(entrevista);
		return entrevistaSalva;
	}
	
	
	@Transactional
	public Entrevista atualizarEntrevista(Integer id, Entrevista entrevista) {
		
		Entrevista entrevistaAtualizada = entrevistaRepository.getReferenceById(id);
		entrevistaAtualizada.setData(entrevista.getData());
		entrevistaAtualizada.setComparecimento(entrevista.getComparecimento());
		entrevistaAtualizada.setCandidato(entrevista.getCandidato());
		entrevistaAtualizada.setRecrutador(entrevista.getRecrutador());
		entrevistaAtualizada.setStatusCandidato(entrevista.getStatusCandidato());
		
		return entrevistaRepository.save(entrevistaAtualizada);
	}
}
