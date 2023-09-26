package com.example.app.model.entities;

import java.io.Serializable;

import com.example.app.utils.EstadoInscricao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "candidatura_relatorios")
public class CandidaturaRelatorio implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long candidaturaId;
	private Long vagaId;
	private String vagaTitulo;
	private Long candidatoId;
	private String candidatoNome;
	private EstadoInscricao estadoInscricao;
	
	public CandidaturaRelatorio() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCandidaturaId() {
		return candidaturaId;
	}

	public void setCandidaturaId(Long candidaturaId) {
		this.candidaturaId = candidaturaId;
	}

	public Long getVagaId() {
		return vagaId;
	}

	public void setVagaId(Long vagaId) {
		this.vagaId = vagaId;
	}

	public String getVagaTitulo() {
		return vagaTitulo;
	}

	public void setVagaTitulo(String vagaTitulo) {
		this.vagaTitulo = vagaTitulo;
	}

	public Long getCandidatoId() {
		return candidatoId;
	}

	public void setCandidatoId(Long candidatoId) {
		this.candidatoId = candidatoId;
	}

	public String getCandidatoNome() {
		return candidatoNome;
	}

	public void setCandidatoNome(String candidatoNome) {
		this.candidatoNome = candidatoNome;
	}

	public EstadoInscricao getEstadoInscricao() {
		return estadoInscricao;
	}

	public void setEstadoInscricao(EstadoInscricao estadoInscricao) {
		this.estadoInscricao = estadoInscricao;
	}
	
}
