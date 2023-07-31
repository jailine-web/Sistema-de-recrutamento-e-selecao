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
<<<<<<< HEAD
	
	@Email
=======
	private String telefone;
>>>>>>> 348dd47a5a5e8e285ff0dc19e5dd0c13a5b7af82
	private String email;
	
	@NotBlank
	private String usuario;
	
	@NotBlank
	private String senha;
	
	private boolean recrutador;
<<<<<<< HEAD
	
	@Lob
	private String curriculo;
=======
	@Lob
	@Column(length = 10485760)
	private byte[] curriculo;
>>>>>>> 348dd47a5a5e8e285ff0dc19e5dd0c13a5b7af82
	private String img;
	private String time;
	
	public Recrutador() {
		
	}
	
<<<<<<< HEAD
	public Recrutador(Integer id, String nome, String email, String usuario, String senha,boolean recrutador, 
			String curriculo, String img, String time) {
=======
	public Recrutador(Integer id, String nome, String telefone, String email, boolean recrutador, String img, String time) {
>>>>>>> 348dd47a5a5e8e285ff0dc19e5dd0c13a5b7af82
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.usuario = usuario;
		this.senha = senha;
		this.recrutador = recrutador;
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
