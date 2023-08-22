package com.example.app.services;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.controller.excecao.Tratamentoexcecao;
import com.example.app.model.entities.Vaga;
import com.example.app.repositories.CandidatoRepository;
import com.example.app.repositories.VagaRepository;
import com.example.app.utils.EstadoVaga;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class VagaService {

	@Autowired
	private VagaRepository vr;

	@Autowired
	private CandidatoRepository cr;

	@Transactional
	public List<Vaga> buscarTodos() {
		List<Vaga> listaVagas = vr.findAll();
		return listaVagas;
	}

	@Transactional
	public Vaga inserirVaga(Vaga vaga) {

		if (vaga.getDataAbertura() == null) {
			vaga.setDataAbertura(LocalDateTime.now());
		}
		if (vaga.getEstadoVaga() == null) {
			vaga.setEstadoVaga(EstadoVaga.ATIVA);
		}

		Vaga v = vr.save(vaga);
		return v;
	}

	@Transactional
	public Vaga buscarPorId(Integer id) {
		Vaga vaga = vr.findById(id).get();
		return vaga;
	}

	@Transactional
	public Vaga atualizarVaga(Integer id, Vaga vaga) {
		try {

			Vaga vagaAtualizada = vr.getReferenceById(id);
			atualizarDados(vagaAtualizada, vaga);
			return vr.save(vagaAtualizada);
		} catch (EntityNotFoundException e) {
			throw new Tratamentoexcecao("O id: " + id + " recrutador não foi encontrado ");
		}

	}

	private void atualizarDados(Vaga vagaAtualizada, Vaga vaga) {

		vagaAtualizada.setTitulo(vaga.getTitulo());
		vagaAtualizada.setDescricao(vaga.getDescricao());
		vagaAtualizada.setRequisitos(vaga.getRequisitos());
		vagaAtualizada.setLocalizacao(vaga.getLocalizacao());
		vagaAtualizada.setFormato(vaga.getFormato());

	}

	@Transactional
	public Vaga fecharVaga(Integer id) {

		Vaga vaga = vr.getReferenceById(id);
		if (vaga.getEstadoVaga() == EstadoVaga.ATIVA) {
			vaga.setEstadoVaga(EstadoVaga.FECHADA);
			vaga.setDataFechamento(LocalDateTime.now());
		}
		return vr.save(vaga);
	}

	@Transactional
	public Long vagaAtiva(Integer id) {
		long dias = 0;

		Vaga vaga = vr.getReferenceById(id);

		if(vaga.getEstadoVaga() == EstadoVaga.FECHADA) {
			
			LocalDateTime dateAbertura = vaga.getDataAbertura();
			LocalDateTime dataFechamento = vaga.getDataFechamento();
			
			dias = ChronoUnit.DAYS.between(dateAbertura, dataFechamento);
		}else {
			dias = ChronoUnit.DAYS.between(vaga.getDataAbertura(), LocalDateTime.now());
		}

		return dias;
	}
}