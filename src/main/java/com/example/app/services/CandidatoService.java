package com.example.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.app.DTO.CandidatoDTO;
import com.example.app.DTO.CandidatoReduzido;
import com.example.app.excecao.Tratamentoexcecao;
import com.example.app.model.entities.Candidato;
import com.example.app.projection.VagasProjection;
import com.example.app.repositories.CandidatoRepository;

import jakarta.transaction.Transactional;

@Service
public class CandidatoService {

	@Autowired
	private CandidatoRepository cp;

	@Transactional
	public List<Candidato> buscarTodos() {
		List<Candidato> listaRecrutador = cp.findAll();
		return listaRecrutador;
	}
	
	@Transactional
	public Candidato buscarPorId(Integer id) {
		
		Candidato candBanco = cp.findById(id).get();
		if( id != candBanco.getId()) {
			throw new Tratamentoexcecao("Objeto não encontrado");
		}
	
		return candBanco;
	}
	
	public Candidato inserirCandidato(Candidato candidato) {
		Candidato c = cp.save(candidato);
		return c;
	}
	
	public void excluirCandidato(Integer id) {
		
		try{
			
			Candidato c = buscarPorId(id);
			Integer idC = c.getId();
			
			if(idC == id) {
				cp.deleteById(id);
			}
		}
		catch(EmptyResultDataAccessException e) {
			throw new Tratamentoexcecao("Candidato não encontrado");
		}
	}
	
	public List<CandidatoReduzido> buscarCandidatos(Integer idVagas){
		List<VagasProjection> resultado = cp.buscarCandidatosDaVaga(idVagas);
		return resultado.stream().map(x -> new CandidatoReduzido(x)).toList();
	}
	
}
