package com.example.app.utils;

public enum EstadoVaga {

	ATIVA("a"),
	FECHADA("f");
	
	private String estado;
	
	 EstadoVaga (String estado) {
		this.estado = estado;
	}
	
	 public String getEstadoVaga() {
		 return estado;
	 }
}
