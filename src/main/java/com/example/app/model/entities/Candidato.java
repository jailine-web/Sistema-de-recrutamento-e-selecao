package com.example.app.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;

import com.example.app.projection.CandidaturasProjection;
import com.example.app.utils.Notas;
import com.example.app.utils.StatusCurriculoAvaliado;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

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
	
	@Email
	@Column(unique= true)
	private String email;
	private boolean recrutador;

	@Lob
	@Column(length = 1000000) // currículo de até 1MB
	private byte[] curriculo;
	private String semestreVigente;
	private String curso;
	private String termino;
	private String instituicao;
	private String localizacao;
	
	@Enumerated(EnumType.ORDINAL)
	private Notas notas; 
	
	@Column(name = "curriculo_avaliado")
	@Enumerated(EnumType.STRING)
	private StatusCurriculoAvaliado curriculoAvaliado;
	
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

	public Candidato(Integer id, String nome, String telefone,@Email String email, boolean recrutador,
			String semestreVigente, String curso, String termino, String instituicao, 
			String localizacao) {

		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.recrutador = recrutador;
		this.semestreVigente = semestreVigente;
		this.curso = curso;
		this.termino = termino;
		this.instituicao = instituicao;
		this.localizacao = localizacao;
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
	
	public String getLocalizacao() {
		return localizacao;
	}

	public Notas getNotas() {
		return notas;
	}

	public void setNotas(Notas notas) {
		this.notas = notas;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	
	@JsonIgnore
	public List<Candidatura> getCandidaturas() {
		return candidaturas;
	}

	public void setCandidaturas(List<Candidatura> candidaturas) {
		this.candidaturas = candidaturas;
	}

	public StatusCurriculoAvaliado getCurriculoAvaliado() {
		return curriculoAvaliado;
	}

	public void setCurriculoAvaliado(StatusCurriculoAvaliado curriculoAvaliado) {
		this.curriculoAvaliado = curriculoAvaliado;
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

}