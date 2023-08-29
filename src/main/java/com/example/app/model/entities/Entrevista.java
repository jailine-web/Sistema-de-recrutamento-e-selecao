package com.example.app.model.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import com.example.app.utils.StatusCandidato;
import com.example.app.utils.StatusComparacimento;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="entrevistas")
public class Entrevista implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private LocalDateTime data;
	
	@Enumerated(EnumType.STRING)
	private StatusComparacimento comparecimento;
	
	private Candidato candidato;
	private Recrutador recrutador;
	
	@Enumerated(EnumType.STRING)
	private StatusCandidato statusCandidato;
	
	public Entrevista() {
		
	}
	
	public Entrevista(Integer id, StatusComparacimento comparecimento, StatusCandidato statusCandidato) {
		this.id = id;
		//this.data = data;
		this.comparecimento = comparecimento;
		this.statusCandidato = statusCandidato;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public StatusComparacimento getComparecimento() {
		return comparecimento;
	}

	public void setComparecimento(StatusComparacimento comparecimento) {
		this.comparecimento = comparecimento;
	}

	public StatusCandidato getStatusCandidato() {
		return statusCandidato;
	}

	public void setStatusCandidato(StatusCandidato statusCandidato) {
		this.statusCandidato = statusCandidato;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public Recrutador getRecrutador() {
		return recrutador;
	}

	public void setRecrutador(Recrutador recrutador) {
		this.recrutador = recrutador;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
		Entrevista other = (Entrevista) obj;
		return Objects.equals(id, other.id);
	}

	

}
