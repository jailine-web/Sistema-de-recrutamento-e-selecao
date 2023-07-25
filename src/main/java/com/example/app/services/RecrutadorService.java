package com.example.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.app.DTO.CandidatoReduzido;
import com.example.app.controller.excecao.IdNaoEncontrado;
import com.example.app.controller.excecao.Tratamentoexcecao;
import com.example.app.model.entities.Recrutador;
import com.example.app.projection.CandidaturasProjection;
import com.example.app.repositories.CandidatoRepository;
import com.example.app.repositories.RecrutadorRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class RecrutadorService {

	@Autowired
	private RecrutadorRepository rr;
	
	@Autowired
	private CandidatoRepository cr;
	
	@Transactional
	public List<Recrutador> buscarTodos(){
		List<Recrutador> listaRecrutador = rr.findAll();
		return listaRecrutador;
	}
	
	@Transactional
	public Recrutador inserirRecrutador(Recrutador recrutador) {
		Recrutador r = rr.save(recrutador);
		return r;
	}
	
	@Transactional
	public List<CandidatoReduzido> buscarCandidatos(Integer idVagas) {
		
		List<CandidaturasProjection> resultado = cr.buscarCandidatosDaVaga(idVagas);
		return resultado.stream().map(x -> new CandidatoReduzido(x)).toList();
	}
	
	@Transactional
	public Recrutador buscarPorId(Integer id) {
		
		Recrutador rBanco = rr.findById(id).get();
		return rBanco;
	}
	
	@Transactional
	public Recrutador atualizar(Integer id ,Recrutador recrutador) {
		try {
			
			Recrutador recrutadorAtualizado = rr.getReferenceById(id);
			atualizarDados(recrutadorAtualizado, recrutador);
			return rr.save(recrutadorAtualizado);
		}
		catch(EntityNotFoundException e) {
			
			throw new IdNaoEncontrado(id);
		}
	}

	private void atualizarDados( Recrutador r1, Recrutador r2) {
		
		r1.setNome(r2.getNome());
		r1.setEmail(r2.getEmail());
		r1.setRecrutador(r2.isRecrutador());
		r1.setCurriculo(r2.getCurriculo());
		r1.setImg(r2.getImg());
		r1.setTime(r2.getTime());
	}
	
	public void excluirRecrutador(Integer id) {
		try {
			
			buscarPorId(id);
			rr.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new Tratamentoexcecao("Recrutador não encontrado");
		}
	}
	
}
