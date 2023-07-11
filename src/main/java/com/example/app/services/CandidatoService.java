package com.example.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.excecao.Tratamentoexcecao;
import com.example.app.model.entities.Candidato;
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
		
		Candidato idBanco = cp.findById(id).get();
		if( id != idBanco.getId()) {
			throw new Tratamentoexcecao("Objeto não encontrado");
		}
	
		return idBanco;
	}
	
}
