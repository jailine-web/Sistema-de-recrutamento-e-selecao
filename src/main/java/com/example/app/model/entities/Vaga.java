package com.example.app.model.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.example.app.utils.EstadoVaga;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Vaga implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String titulo;
	private String descricao;
	private String requisitos;
	private String localizacao;
	private String formato;
	private LocalDateTime dataAbertura;
	private LocalDateTime dataFechamento;
	private String categoria;
	
	@Enumerated(EnumType.STRING)
	private EstadoVaga estadoVaga;
	
	@OneToMany(mappedBy = "vaga", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Questionario> questionarios = new ArrayList<>();
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "candidato_id")
	private Candidato candidato;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "vaga")
	private List<Candidatura> candidaturas;

	public Vaga() {
		super();
	}

	public Vaga(Integer id, String titulo, String descricao, String requisitos, String localizacao, 
			String formato, String categoria, LocalDateTime dataAbertura) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.requisitos = requisitos;
		this.localizacao = localizacao;
		this.formato = formato;
		this.categoria = categoria;
//		setDataAbertura(LocalDateTime.now());
		this.dataAbertura = dataAbertura;
		setEstadoVaga(estadoVaga.ATIVA);
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(String requisitos) {
		this.requisitos = requisitos;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	
	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDateTime dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public List<Questionario> getQuestionarios() {
		return questionarios;
	}

	public void setQuestionarios(List<Questionario> questionarios) {
		this.questionarios = questionarios;
	}

	public List<Candidatura> getCandidaturas() {
		return candidaturas;
	}

	public void setCandidaturas(List<Candidatura> candidaturas) {
		this.candidaturas = candidaturas;
	}

	
	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public EstadoVaga getEstadoVaga() {
		return estadoVaga;
	}

	public void setEstadoVaga(EstadoVaga estadoVaga) {
		this.estadoVaga = estadoVaga;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vaga other = (Vaga) obj;
		return Objects.equals(id, other.id) && Objects.equals(titulo, other.titulo);
	}
	

}
