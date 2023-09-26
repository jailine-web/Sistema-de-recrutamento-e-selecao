package com.example.app.DTO;

import java.util.List;

public class QuestionarioDTO {
	
	private String titulo;
	
	private List<PerguntaDTO> perguntas;
		
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public List<PerguntaDTO> getPerguntas() {
		return perguntas;
	}
	
	public void setPerguntas(List<PerguntaDTO> pergunta) {
		this.perguntas = pergunta;
	}
	
	
}
