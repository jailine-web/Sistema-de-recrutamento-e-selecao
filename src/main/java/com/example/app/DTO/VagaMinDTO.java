package com.example.app.DTO;

import org.springframework.beans.BeanUtils;

import com.example.app.projection.CandidaturasCandidatoProjection;

public class VagaMinDTO {

	private String nome;

	public VagaMinDTO(String nome) {
		this.nome = nome;
	}
	
	public VagaMinDTO(CandidaturasCandidatoProjection ccp) {
		BeanUtils.copyProperties(ccp, this);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
