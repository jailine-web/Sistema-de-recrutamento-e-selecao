package com.example.app.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Alerta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer candidaturaId;
	private String mensagemDeAlerta;
	
	public Alerta() {
	}

	public Integer getCandidaturaId() {
		return candidaturaId;
	}

	public void setCandidaturaId(Integer candidaturaId) {
		this.candidaturaId = candidaturaId;
	}

	public String getMensagem() {
		return mensagemDeAlerta;
	}

	public void setMensagem(String mensagemDeAlerta) {
		this.mensagemDeAlerta = mensagemDeAlerta;
	}
}
