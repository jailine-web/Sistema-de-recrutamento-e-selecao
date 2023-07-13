package com.example.app.controller.excecao;

public class IdNaoEncontrado extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public IdNaoEncontrado(Object id) {
		super("Código não encontrado para o Id: "+ id);
		
	}
}
