package com.example.app.utils;

public enum Regras {
	ADMIN("admin"),
	ESTAGIARIO("estagiario"),
	CANDIDATO("candidato");
	
	private String regras;
	
	Regras(String regras) {
		this.regras = regras;
	}
	
	public String getRegra() {
		return regras;
	}

}
