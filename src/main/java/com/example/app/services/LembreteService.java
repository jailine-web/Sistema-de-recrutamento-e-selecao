package com.example.app.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.app.controller.excecao.Tratamentoexcecao;
import com.example.app.model.entities.Entrevista;
import com.example.app.model.entities.Lembrete;
import com.example.app.repositories.EntrevistaRepository;
import com.example.app.repositories.LembreteRepository;

import jakarta.transaction.Transactional;

@Service
public class LembreteService {

	@Autowired
	private LembreteRepository lr;
	
	@Autowired
	private EntrevistaRepository entrevistaRepository;
	
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
		} catch (EmptyResultDataAccessException e) {
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
	public List<Lembrete> buscarLembretes() {
		List<Lembrete> listaLembretes = lr.findAll();
		return listaLembretes;
	}
}
