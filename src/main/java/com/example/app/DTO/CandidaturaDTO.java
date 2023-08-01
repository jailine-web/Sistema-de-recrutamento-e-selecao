package com.example.app.DTO;

public class CandidaturaDTO {
	private Long id;
	private CandidatoReduzido candidato;
	
	public CandidaturaDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CandidatoReduzido getCandidato() {
		return candidato;
	}

	public void setCandidato(CandidatoReduzido candidato) {
		this.candidato = candidato;
	}
	
	
}
