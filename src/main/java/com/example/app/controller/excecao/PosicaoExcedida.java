package com.example.app.controller.excecao;

import java.io.Serializable;

public class PosicaoExcedida implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long momento;
	private Integer erro;
	private String descricao;
	private String mensagem;
	private String caminho;
	
	public PosicaoExcedida() {
		
	}

	public PosicaoExcedida(Long momento, Integer erro, String descricao, String mensagem, String caminho) {
		this.momento = momento;
		this.erro = erro;
		this.descricao = descricao;
		this.mensagem = mensagem;
		this.caminho = caminho;
	}

	public Long getMomento() {
		return momento;
	}

	public void setMomento(Long momento) {
		this.momento = momento;
	}

	public Integer getErro() {
		return erro;
	}

	public void setEstado(Integer erro) {
		this.erro = erro;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
	
	
}
