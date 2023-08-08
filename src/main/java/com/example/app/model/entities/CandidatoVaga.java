package com.example.app.model.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class CandidatoVaga implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private CandidatoVagaPK chaveCompostaPK = new CandidatoVagaPK();

	public CandidatoVaga() {
		
	}
	
	public CandidatoVaga(Candidato candidato, Vaga vaga) {
		chaveCompostaPK.setCandidato(candidato);
		chaveCompostaPK.setVaga(vaga);
	}

	public CandidatoVagaPK getChaveCompostaPK() {
		return chaveCompostaPK;
	}

	public void setChaveCompostaPK(CandidatoVagaPK chaveCompostaPK) {
		this.chaveCompostaPK = chaveCompostaPK;
	}
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(chaveCompostaPK);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CandidatoVaga other = (CandidatoVaga) obj;
		return Objects.equals(chaveCompostaPK, other.chaveCompostaPK);
	}


}
