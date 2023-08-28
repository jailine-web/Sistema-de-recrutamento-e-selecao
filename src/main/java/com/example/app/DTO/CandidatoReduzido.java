package com.example.app.DTO;

import org.springframework.beans.BeanUtils;

import com.example.app.model.entities.Candidato;
import com.example.app.projection.CandidaturasProjection;

public class CandidatoReduzido {

	private Integer id;
	private String nome;
	private String telefone;
	private String email;
	private String curso;
	private String semestreVigente;

	public CandidatoReduzido() {
	}
	
	public CandidatoReduzido(Candidato candidato) {
		
		id = candidato.getId();
		nome = candidato.getNome();
		telefone = candidato.getTelefone();
		email = candidato.getEmail();
		curso = candidato.getCurso();
		semestreVigente = candidato.getSemestreVigente();
	}

	public CandidatoReduzido(CandidaturasProjection projection) {

		BeanUtils.copyProperties(projection, this);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getSemestreVigente() {
		return semestreVigente;
	}

	public void setSemestreVigente(String semestreVigente) {
		this.semestreVigente = semestreVigente;
	}

}
