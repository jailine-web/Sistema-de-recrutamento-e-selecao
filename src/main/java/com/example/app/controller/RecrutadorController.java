package com.example.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.model.entities.Recrutador;
import com.example.app.services.RecrutadorService;

@RestController
@RequestMapping(value="/recrutador")
public class RecrutadorController {

	@Autowired
	private RecrutadorService rs;
	
	@GetMapping
	public List<Recrutador>buscarTodos(){
		List<Recrutador> listaRecrutador = rs.buscarTodos();
		return listaRecrutador;
	}
}
