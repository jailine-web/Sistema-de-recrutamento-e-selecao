package com.example.app.model.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import com.example.app.utils.EstadoInscricao;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "candidaturas")
public class Candidatura implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "vaga_id")
	private Vaga vaga;
	
	@ManyToOne
	@JoinColumn(name = "candidato_id")
	private Candidato candidato;
	
	@Enumerated(EnumType.STRING)
	private EstadoInscricao estado;
	
	private LocalDateTime dataInscricao;
	
	public Candidatura() {
		
	}
	
	public Candidatura(Long id, Vaga vaga, Candidato candidato, 
					   EstadoInscricao estado, LocalDateTime dataInscricao) {
		this.id = id; 
		this.vaga = vaga;
		this.candidato = candidato;
		this.estado = estado;
		this.dataInscricao = dataInscricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Vaga getVaga() {
		return vaga;
	}

	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}
	
	public EstadoInscricao getEstado() {
		return estado;
	}

	public void setEstado(EstadoInscricao estado) {
		this.estado = estado;
	}
	
	public LocalDateTime getDataInscricao() {
		return dataInscricao;
	}
	
	public void setDataInscricao(LocalDateTime dataInscricao) {
		this.dataInscricao = dataInscricao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Candidatura other = (Candidatura) obj;
		return Objects.equals(id, other.id);
	}
}
