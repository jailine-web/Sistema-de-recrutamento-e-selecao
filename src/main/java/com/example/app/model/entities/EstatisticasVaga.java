package com.example.app.model.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class EstatisticasVaga implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Long numeroDeVisualizacoes;
	private Long numeroDeCandidaturas;
	private Long taxaDeConversao;
	
	public EstatisticasVaga() {

	}

	public EstatisticasVaga(Integer id, Long numeroDeVisualizacoes, Long numeroDeCandidaturas, Long taxaDeConversao) {
		this.id = id;
		this.numeroDeVisualizacoes = numeroDeVisualizacoes;
		this.numeroDeCandidaturas = numeroDeCandidaturas;
		this.taxaDeConversao = taxaDeConversao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getNumeroDeVisualizacoes() {
		return numeroDeVisualizacoes;
	}

	public void setNumeroDeVisualizacoes(Long numeroDeVisualizacoes) {
		this.numeroDeVisualizacoes = numeroDeVisualizacoes;
	}

	public Long getNumeroDeCandidaturas() {
		return numeroDeCandidaturas;
	}

	public void setNumeroDeCandidaturas(Long numeroDeCandidaturas) {
		this.numeroDeCandidaturas = numeroDeCandidaturas;
	}

	public Long getTaxaDeConversao() {
		return taxaDeConversao;
	}

	public void setTaxaDeConversao(Long taxaDeConversao) {
		this.taxaDeConversao = taxaDeConversao;
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
		EstatisticasVaga other = (EstatisticasVaga) obj;
		return Objects.equals(id, other.id);
	}
	

}
