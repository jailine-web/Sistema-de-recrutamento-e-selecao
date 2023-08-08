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
	
	private Long candidaturaId;
	private String mensagem;
	
	public Alerta() {
	}

	public Long getCandidaturaId() {
		return candidaturaId;
	}

	public void setCandidaturaId(Long candidaturaId) {
		this.candidaturaId = candidaturaId;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
