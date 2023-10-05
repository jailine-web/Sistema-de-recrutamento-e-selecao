package com.example.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.model.entities.Candidatura;
import com.example.app.repositories.CandidaturaRepository;
import com.example.app.utils.EstadoInscricao;
import com.example.app.utils.Notas;

import jakarta.transaction.Transactional;

@Service
public class CandidaturaService {
	
	@Autowired
	private CandidaturaRepository candidaturaRepository;
	

	@Transactional
	public List<Candidatura> candidaturasSelecionadas() {
	
		List<Candidatura> candidaturas = candidaturaRepository.findAll();
		
		for(Candidatura c : candidaturas) {
			Candidatura candidatura = candidaturaRepository.getReferenceById(c.getId());
			Notas n = candidatura.getCandidato().getNotas();
			
			if(n != Notas.NOTA0) {
				candidatura.setEstado(EstadoInscricao.SELECIONADO);
			}
			else {
				candidatura.setEstado(EstadoInscricao.INADEQUADO);
			}
		}
		return candidaturas;
	}
}
