package com.example.app.controller.excecao;

public class Tratamentoexcecao extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public Tratamentoexcecao(String msg) {
		super(msg);
	}

}
