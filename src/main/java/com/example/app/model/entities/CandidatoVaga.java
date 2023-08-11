package com.example.app.model.entities;

import com.example.app.model.entities.PK.CandidatoVagaPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table()
public class CandidatoVaga {
	
	@EmbeddedId
	private CandidatoVagaPK chaveComposta = new CandidatoVagaPK();

	public CandidatoVaga() {

	}

	public CandidatoVaga(Candidato candidato, Vaga vaga) {
		chaveComposta.setCandidato(candidato);
		chaveComposta.setVaga(vaga);
	}
	
	@JsonIgnore
	public Candidato getCandidato() {
		return chaveComposta.getCandidato();
	}
	
	public void setCandidato(Candidato candidato) {
		chaveComposta.setCandidato(candidato);
	}

	public Vaga getVaga() {
		return chaveComposta.getVaga();
	}
	
	public void setVaga(Vaga vaga) {
		chaveComposta.setVaga(vaga);
	}
	

}
