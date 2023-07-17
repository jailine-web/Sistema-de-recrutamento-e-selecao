package com.example.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.model.entities.Vaga;
import com.example.app.repositories.VagaRepository;

@Service
public class VagaService {

	@Autowired
	private VagaRepository vr;
	
	
	public List<Vaga> buscarTodos(){
		List<Vaga> listaVagas = vr.findAll();
		return listaVagas;
	}
	
	public Vaga buscarPorId(Integer id) {
		Vaga vaga = vr.findById(id).get();
		return vaga;
	}
	
	
}
