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
	private String mensagem;
	
	public Alerta() {
	}

	public Integer getCandidaturaId() {
		return candidaturaId;
	}

	public void setCandidaturaId(Integer candidaturaId) {
		this.candidaturaId = candidaturaId;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
