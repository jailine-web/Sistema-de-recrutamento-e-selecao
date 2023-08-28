package com.example.app.utils;

public enum StatusComparacimento {

	COMPARECEU("compareceu"),
	NAO_COMPARECEU("nao_compareceu"),
	DESISTIU("desistiu");
	
	private String comparecimento;
	
	StatusComparacimento(String comparecimento){
		this.comparecimento = comparecimento;
	}
	
	public String getComparecimento() {
		return comparecimento;
	}
	
}
