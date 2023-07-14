package com.example.app.DTO;

import org.springframework.beans.BeanUtils;

import com.example.app.model.entities.Candidato;
import com.example.app.projection.CandidaturasProjection;
import com.example.app.model.entities.Candidato;

public class CandidatoDTO {

	private Integer id;
	private String nome;
	private String telefone;
	private String email;
	private boolean recrutador;
	private String curriculo;
	private String img;
	private String semestreVigente;
	private String curso;
	private String termino;
	private String instituicao;
	
	public CandidatoDTO() {
		
	}
	
	public CandidatoDTO(Candidato candidato) {
		
		BeanUtils.copyProperties(candidato, this);
		
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

	public boolean isRecrutador() {
		return recrutador;
	}

	public void setRecrutador(boolean recrutador) {
		this.recrutador = recrutador;
	}

	public String getCurriculo() {
		return curriculo;
	}

	public void setCurriculo(String curriculo) {
		this.curriculo = curriculo;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getSemestreVigente() {
		return semestreVigente;
	}

	public void setSemestreVigente(String semestreVigente) {
		this.semestreVigente = semestreVigente;
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

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}
	
}
