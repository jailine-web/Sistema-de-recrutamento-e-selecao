package com.example.app.model.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
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
@Table
public class Entrevista implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private LocalDateTime data;
	
	@Enumerated(EnumType.STRING)
	private StatusComparacimento presenca;
	
	@Enumerated(EnumType.STRING)
	private StatusCandidato situacao;
	
	private String dataRecebida;
	public Entrevista() {
		
	}

	public Entrevista(Integer id, String data,StatusComparacimento presenca, StatusCandidato situacao) {
		this.id= id;
		this.data = LocalDateTime.parse(dataRecebida);
		this.presenca = presenca;
		this.situacao = situacao;
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

	public StatusComparacimento getPresenca() {
		return presenca;
	}

	public void setPresenca(StatusComparacimento presenca) {
		this.presenca = presenca;
	}

	public StatusCandidato getSituacao() {
		return situacao;
	}

	public void setSituacao(StatusCandidato situacao) {
		this.situacao = situacao;
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
