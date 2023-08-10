package com.example.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.repositories.EstatisticasRepository;
import com.example.app.repositories.VagaRepository;

import jakarta.transaction.Transactional;

@Service
public class EstatisticasVagaService {
	
	@Autowired
	private EstatisticasRepository er;
	
	@Autowired
	private VagaRepository vr;
	
	@Transactional
	public Long quantidadeDeCandidaturas(Integer idVaga){
		Long candidaturas = er.quantidadeDeCandidaturas(idVaga);
		return candidaturas;
	}
	
	
}
