package com.example.app.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;

import com.example.app.projection.CandidaturasProjection;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@JsonInclude(Include.NON_NULL)
@Table
public class Candidato implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;
	private String telefone;
	private String email;
	private boolean recrutador;

	@Lob
	@Column(length = 3145728) // currículo de até 3MB
	private byte[] curriculo;
	private String img;
	private String semestreVigente;
	private String curso;
	private String termino;
	private String instituicao;

	
	@OneToMany(mappedBy = "candidato")
	private List<Candidatura> candidaturas;
	
	@JsonIgnore
	@OneToMany(mappedBy = "candidato")
	private List<Vaga> vagas = new ArrayList<>();

	public Candidato() {

	}

	public Candidato(CandidaturasProjection projection) {
		BeanUtils.copyProperties(projection, this);
	}

	public Candidato(Integer id, String nome, String telefone, String email, boolean recrutador, String img,
			String semestreVigente, String curso, String termino, String instituicao) {

		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.recrutador = recrutador;
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

	public byte[] getCurriculo() {
		return curriculo;
	}

	public void setCurriculo(byte[] curriculo) {
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
	
	public List<Vaga> getVagas() {
		return vagas;
	}

	public void addVaga(Vaga v) {
		vagas.add(v);
	}

	public void removerVaga(Vaga v) {
		vagas.remove(v);
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
		// Frontend CSS e mapeamento de botões
	}
}