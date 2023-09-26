package com.example.app.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

		vaga.setEstadoVaga(EstadoVaga.ATIVA);
		vaga.setDataAbertura(LocalDateTime.now());
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
	
	public void excluirVaga(Integer id) {
		try {
			
			buscarPorId(id);
			vr.deleteById(id);
		} 
		catch(EmptyResultDataAccessException e) {
			throw new Tratamentoexcecao("Vaga não encontrada");
		}
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
			
			if(dias<1) {
				dias = ChronoUnit.HOURS.between(dateAbertura, dataFechamento);
			}
			
		}
		
		if(vaga.getEstadoVaga()== EstadoVaga.ATIVA) {
			
			dias = ChronoUnit.DAYS.between(vaga.getDataAbertura(), LocalDateTime.now());
			if(dias<1) {
				dias = ChronoUnit.HOURS.between(vaga.getDataAbertura(), LocalDateTime.now());
				
			}
			return dias;
		}
		return dias;
	}
}