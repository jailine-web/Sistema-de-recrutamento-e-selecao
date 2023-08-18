package com.example.app.utils;

public enum Regras {
	ADMIN("admin"),
	ESTAGIARIOS("est"),
	CANDIDATOS("cand");
	
	private String regras;
	
	Regras(String regras) {
		this.regras = regras;
	}
	
	public String getRegra() {
		return regras;
	}

}
