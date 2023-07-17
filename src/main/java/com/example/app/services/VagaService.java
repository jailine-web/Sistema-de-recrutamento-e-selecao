package com.example.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.controller.excecao.IdNaoEncontrado;
import com.example.app.model.entities.Vaga;
import com.example.app.repositories.VagaRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class VagaService {

	@Autowired
	private VagaRepository vr;
	
	@Transactional
	public List<Vaga> buscarTodos(){
		List<Vaga> listaVagas = vr.findAll();
		return listaVagas;
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
		} 
		catch(EntityNotFoundException e) {
			throw new IdNaoEncontrado(id);
		}
		
	}

	private void atualizarDados(Vaga vagaAtualizada, Vaga vaga) {
		
			vagaAtualizada.setNome(vaga.getNome());
			vagaAtualizada.setDescricao(vaga.getDescricao());
			vagaAtualizada.setRequisitos(vaga.getRequisitos());
			vagaAtualizada.setLocalizacao(vaga.getLocalizacao());
			vagaAtualizada.setFormato(vaga.getFormato());
		
	}
	
}
