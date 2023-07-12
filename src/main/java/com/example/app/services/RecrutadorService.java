package com.example.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.model.entities.Candidato;
import com.example.app.model.entities.Recrutador;
import com.example.app.repositories.RecrutadorRepository;

@Service
public class RecrutadorService {

	@Autowired
	private RecrutadorRepository rp;
	
	public List<Recrutador> buscarTodos(){
		List<Recrutador> listaRecrutador = rp.findAll();
		return listaRecrutador;
	}
	
	public Recrutador inserirRecrutador(Recrutador recrutador) {
		Recrutador r = rp.save(recrutador);
		return r;
	}
}
