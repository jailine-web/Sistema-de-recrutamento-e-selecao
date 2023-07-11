package com.example.app.model.entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table()
public class Candidato {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public Candidato() {
		
	}
	
	public Candidato(Integer id, String nome, String telefone,String email, boolean recrutador, String curriculo, String img,
			String semestreVigente, String curso, String termino, String instituicao) {
		
		this.id = id;
		this.nome =nome;
		this.telefone = telefone;
		this.email = email;
		this.recrutador = recrutador;
		this.curriculo = curriculo;
		this.img = img;
		this.semestreVigente = semestreVigente;
		this.curso = curso;
		this.termino = termino;
		this.instituicao = instituicao;
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
	
	@Override
	public int hashCode() {
		return Objects.hash(email, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Candidato other = (Candidato) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id);
	}

	public void apresentarAreaLogada() {
		
	}

	
	
}
