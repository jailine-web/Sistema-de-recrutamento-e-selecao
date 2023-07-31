package com.example.app.services;

import org.springframework.stereotype.Service;

import com.example.app.model.entities.Candidato;
import com.example.app.model.entities.Candidatura;
import com.example.app.model.entities.Vaga;
import com.example.app.repositories.CandidatoRepository;
import com.example.app.repositories.CandidaturaRepository;
import com.example.app.repositories.VagaRepository;

@Service
public class CandidaturaService {
	
	private final CandidaturaRepository cr;
	private final CandidatoRepository candidatoRe;
	private final VagaRepository vr;
	
	public CandidaturaService(CandidaturaRepository cr, CandidatoRepository candidatoRe, VagaRepository vr) {
		this.cr = cr;
		this.candidatoRe = candidatoRe;
		this.vr = vr;
	}
	
	public Candidatura criarCandidatura(Candidatura candidatura) {
		Candidato candidato = candidatoRe.findById(candidatura.getCandidato().getId()).orElse(null);
		if (candidato == null) {
			throw new IllegalArgumentException("ID do candidato inválido");
		}
		
		Vaga vaga = vr.findById(candidatura.getVaga().getId()).orElse(null);
		if (vaga == null) {
			throw new IllegalArgumentException("ID da vaga inválido");
		}
		
		boolean candidaturaExistente = cr.existsById(candidatura.getId());
		if (candidaturaExistente) {
			throw new IllegalStateException("Já existe uma candidatura para este Id");
		}
		candidatura.setCandidato(candidato);
		candidatura.setVaga(vaga);
		return cr.save(candidatura);
	}
}
