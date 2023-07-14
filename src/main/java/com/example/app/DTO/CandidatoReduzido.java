package com.example.app.DTO;

import org.springframework.beans.BeanUtils;

import com.example.app.model.entities.Candidato;
import com.example.app.projection.CandidaturasProjection;

public class CandidatoReduzido {

	private Integer id;
	private String nome;
	private String telefone;
	private String curso;
	private String termino;

	public CandidatoReduzido(Candidato candidato) {
		
		id = candidato.getId();
		nome = candidato.getNome();
		telefone = candidato.getTelefone();
		curso = candidato.getCurso();
		termino = candidato.getTermino();
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

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getTermino() {
		return termino;
	}

	public void setTermino(String termino) {
		this.termino = termino;
	}
	
}
