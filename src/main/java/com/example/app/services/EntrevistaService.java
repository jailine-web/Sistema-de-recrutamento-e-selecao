package com.example.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.model.entities.Entrevista;
import com.example.app.repositories.EntrevistaRepository;

import jakarta.transaction.Transactional;

@Service
public class EntrevistaService {

	@Autowired
	private EntrevistaRepository entrevistaRepository;

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

}
