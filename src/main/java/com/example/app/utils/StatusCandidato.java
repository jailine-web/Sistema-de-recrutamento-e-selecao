package com.example.app.utils;

public enum StatusCandidato {
	APROVADO("aprovado"),
	REPROVADO("reprovado");
	
	private String status;
	
	StatusCandidato (String status){
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
	
}
