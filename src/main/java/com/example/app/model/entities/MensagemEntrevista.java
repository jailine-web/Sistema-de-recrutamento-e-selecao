package com.example.app.model.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class MensagemEntrevista implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String titulo;
	
	@Column(columnDefinition = "TEXT")
	private String textoDescritivo;
	private String linkDaSala;
	
	public MensagemEntrevista() {
		
	}

	public MensagemEntrevista(Integer id, String titulo, String textoDescritivo, String linkDaSala) {
		this.id = id;
		this.titulo = titulo;
		this.textoDescritivo = textoDescritivo;
		this.linkDaSala = linkDaSala;
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

	public String getTextoDescritivo() {
		return textoDescritivo;
	}

	public void setTextoDescritivo(String textoDescritivo) {
		this.textoDescritivo = textoDescritivo;
	}

	public String getLinkDaSala() {
		return linkDaSala;
	}

	public void setLinkDaSala(String linkDaSala) {
		this.linkDaSala = linkDaSala;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
		MensagemEntrevista other = (MensagemEntrevista) obj;
		return Objects.equals(id, other.id) && Objects.equals(titulo, other.titulo);
	}

	
}
