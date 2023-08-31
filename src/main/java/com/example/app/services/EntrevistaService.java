package com.example.app.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.app.controller.excecao.Tratamentoexcecao;
import com.example.app.model.entities.Entrevista;
import com.example.app.model.entities.Lembrete;
import com.example.app.model.entities.MensagemEntrevista;
import com.example.app.repositories.EntrevistaRepository;
import com.example.app.repositories.LembreteRepository;
import com.example.app.repositories.MensagemEntrevistaRepository;

import jakarta.transaction.Transactional;

@Service
public class EntrevistaService {

	@Autowired
	private EntrevistaRepository entrevistaRepository;

	@Autowired
	private MensagemEntrevistaRepository mensagemEntrevistaRepository;

	@Autowired
	private LembreteRepository lr;

	@Transactional
	public Entrevista inserirEntrevista(Entrevista entrevista) {
		
		Entrevista entrevistaSalva = entrevistaRepository.save(entrevista);
		return entrevistaSalva;
	}

	@Transactional
	public Entrevista atualizarEntrevista(Integer id, Entrevista entrevista) {

		Entrevista entrevistaAtualizada = entrevistaRepository.getReferenceById(id);
		entrevistaAtualizada.setPresenca(entrevista.getPresenca());
		entrevistaAtualizada.setSituacao(entrevista.getSituacao());

		return entrevistaRepository.save(entrevistaAtualizada);
	}

	@Transactional
	public Entrevista buscarEntrevistaPorId(Integer id) {
		Entrevista entrevista = entrevistaRepository.findById(id).get();
		return entrevista;
	}

	@Transactional
	public void excluirEntrevista(Integer id) {
		buscarEntrevistaPorId(id);
		entrevistaRepository.deleteById(id);
	}

	@Transactional
	public List<Entrevista> getEntrevistas() {
		List<Entrevista> entrevista = entrevistaRepository.findAll();
		return entrevista;
	}

	@Transactional
	public Lembrete inserirLembrete(Lembrete lembrete) {
		Lembrete l = lr.save(lembrete);
		return l;
	}

	@Transactional
	public Lembrete atualizarLembrete(Integer id, Lembrete lembrete) {
		Lembrete lembreteAtualizado = lr.getReferenceById(id);
		lembreteAtualizado.setDescricao(lembrete.getDescricao());
		lr.save(lembreteAtualizado);
		return lembreteAtualizado;
	}

	@Transactional
	public Lembrete buscarLembretePorId(Integer id) {
		Lembrete lembrete = lr.findById(id).get();
		return lembrete;
	}
	
	@Transactional
	public void excluirLembrete(Integer id) {
		try {
			buscarLembretePorId(id);
			lr.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new Tratamentoexcecao("Lembrete não encontrado");
		}
		
	}
	
	@Transactional
	public long enviarLembrete(Integer id) {

		Entrevista entrevista = entrevistaRepository.getReferenceById(id);
		LocalDateTime l = entrevista.getData();
		long horas = ChronoUnit.HOURS.between(l, LocalDateTime.now());
		if (horas == 2) {
			System.out.println("Envie mensagem");
		} else {
			System.out.println("Não é hora ainda");
		}
		return horas;
	}

	
	@Transactional
	public MensagemEntrevista inserirSegundaMensagem(MensagemEntrevista mensagem) {
		MensagemEntrevista msg = mensagemEntrevistaRepository.save(mensagem);
		return msg;
	}

	@Transactional
	public MensagemEntrevista atualizarSegundaMensagem(Integer id, MensagemEntrevista mensagem) {
		MensagemEntrevista msg = mensagemEntrevistaRepository.getReferenceById(id);
		msg.setTitulo(mensagem.getTitulo());
		msg.setTextoDescritivo(mensagem.getTextoDescritivo());
		msg.setLinkDaSala(mensagem.getLinkDaSala());
		mensagemEntrevistaRepository.save(msg);
		return msg;
	}

	@Transactional
	public MensagemEntrevista buscarSegundaMensagemPorId(Integer id) {
		MensagemEntrevista mensagem = mensagemEntrevistaRepository.findById(id).get();
		return mensagem;
	}
	
}
