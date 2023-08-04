package com.example.app.DTO;

import org.springframework.beans.BeanUtils;

import com.example.app.model.entities.Candidato;
import com.example.app.projection.CandidaturasProjection;

public class CandidatoReduzido {

	private Integer id;
	private String nome;
	private byte[] curriculo;

	public CandidatoReduzido() {
	}
	
	public CandidatoReduzido(Candidato candidato) {
		
		id = candidato.getId();
		nome = candidato.getNome();
		curriculo = candidato.getCurriculo();
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

	public byte[] getCurriculo() {
		return curriculo;
	}

	public void setCurriculo(byte[] curriculo) {
		this.curriculo = curriculo;
	}
}
