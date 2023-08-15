package com.example.app.DTO;

import com.example.app.utils.EstadoInscricao;

public class CandidaturaRelatorioDTO {
	private Long candidaturaId;
	private Long vagaId;
	private String vagaTitulo;
	private Long candidatoId;
	private String candidatoNome;
	private EstadoInscricao estadoInscricao;
	
	public CandidaturaRelatorioDTO() {
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
