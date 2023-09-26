package com.example.app.DTO;

import java.util.List;

public class PerguntaDTO {
	
	private String texto;
	
	private List<String> opcoesResposta;
	
	public String getTexto() {
		return texto;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public List<String> getOpcoesResposta() {
		return opcoesResposta;
	}
	
	public void setOpcoesResposta(List<String> opcoesResposta) {
		this.opcoesResposta = opcoesResposta;
	}
	
	
}