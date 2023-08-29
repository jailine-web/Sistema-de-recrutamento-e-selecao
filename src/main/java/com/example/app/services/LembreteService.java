package com.example.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.model.entities.Lembrete;
import com.example.app.repositories.LembreteRepository;

import jakarta.transaction.Transactional;

@Service
public class LembreteService {

	@Autowired
	private LembreteRepository lr;
	
	@Transactional
	public Lembrete inserirLembrete(Lembrete lembrete) {
		Lembrete l = lr.save(lembrete);
		return l;
	}
	
}
