package com.example.app.model.entities.PK;

import java.util.Objects;

import com.example.app.model.entities.Candidato;
import com.example.app.model.entities.Vaga;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class CandidatoVagaPK {
	
	@ManyToOne
	@JoinColumn(name = "candidato_id")
	private Candidato candidato;

	@ManyToOne
	@JoinColumn(name = "vaga_id")
	private Vaga vaga;
	
	public CandidatoVagaPK() {
		
	}

	public CandidatoVagaPK(Candidato candidato, Vaga vaga) {
		this.candidato = candidato;
		this.vaga = vaga;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public Vaga getVaga() {
		return vaga;
	}

	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}

	@Override
	public int hashCode() {
		return Objects.hash(candidato, vaga);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CandidatoVagaPK other = (CandidatoVagaPK) obj;
		return Objects.equals(candidato, other.candidato) && Objects.equals(vaga, other.vaga);
	}
	
}
