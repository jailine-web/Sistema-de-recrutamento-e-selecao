package com.example.app.model.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table()
public class Recrutador implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	private String email;
	private boolean recrutador;
	private String curriculo;
	private String img;
	private String time;
	
	public Recrutador() {
		
	}
	
	public Recrutador(Integer id, String nome, String email, boolean recrutador, 
			String curriculo, String img, String time) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.recrutador = recrutador;
		this.curriculo = curriculo;
		this.img = img;
		this.time = time;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isRecrutador() {
		return recrutador;
	}

	public void setrecrutador(boolean recrutador) {
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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
		Recrutador other = (Recrutador) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id);
	}

	public void apresentarAreaLogada() {
		
	}
}
