package com.example.app.model.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table()
public class Recrutador implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	private String telefone;
	@Email
	private String email;
	
	@NotBlank
	private String usuario;
	
	@NotBlank
	private String senha;
	
	private boolean recrutador;
	
	@Lob
	@Column(length = 1000000) // currículo de até 1MB
	private byte[] curriculo;
	private String time;
	
	public Recrutador() {
		
	}

	public Recrutador(Integer id, String nome, String telefone, String email, 
			String usuario, String senha, boolean recrutador, String time) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.usuario = usuario;
		this.senha = senha;
		this.recrutador = recrutador;
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

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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
